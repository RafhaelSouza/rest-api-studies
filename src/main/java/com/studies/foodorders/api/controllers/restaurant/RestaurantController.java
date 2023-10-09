package com.studies.foodorders.api.controllers.restaurant;

import com.studies.foodorders.api.assemblers.restaurant.RestaurantBasicModelAssembler;
import com.studies.foodorders.api.assemblers.restaurant.RestaurantIdAndNameModelAssembler;
import com.studies.foodorders.api.assemblers.restaurant.RestaurantModelAssembler;
import com.studies.foodorders.api.model.restaurant.RestaurantBasicModel;
import com.studies.foodorders.api.model.restaurant.RestaurantIdAndNameModel;
import com.studies.foodorders.api.model.restaurant.RestaurantModel;
import com.studies.foodorders.api.model.restaurant.input.RestaurantInput;
import com.studies.foodorders.api.openapi.controllers.RestaurantControllerOpenApi;
import com.studies.foodorders.domain.exceptions.BusinessException;
import com.studies.foodorders.domain.exceptions.CityNotFoundException;
import com.studies.foodorders.domain.exceptions.KitchenNotFoundException;
import com.studies.foodorders.domain.exceptions.RestaurantNotFoundException;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.services.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController implements RestaurantControllerOpenApi {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private SmartValidator validator;

    @Autowired
    private RestaurantModelAssembler restaurantModelAssembler;

    @Autowired
    private RestaurantBasicModelAssembler restaurantBasicModelAssembler;

    @Autowired
    private RestaurantIdAndNameModelAssembler restaurantIdAndNameModelAssembler;

    /*@GetMapping
    public MappingJacksonValue list(@RequestParam(required = false) String projection) {
        List<Restaurant> restaurants = restaurantService.list();
        List<RestaurantModel> restaurantsModel = restaurantModelConverter.toCollectionModel(restaurants);

        MappingJacksonValue restaurantsWrapper = new MappingJacksonValue(restaurantsModel);

        restaurantsWrapper.setSerializationView(RestaurantView.Summary.class);

        if ("id-and-name".equals(projection)) {
            restaurantsWrapper.setSerializationView(RestaurantView.IdAndName.class);
        } else if ("full".equals(projection)) {
            restaurantsWrapper.setSerializationView(null);
        }

        return restaurantsWrapper;
    }*/

    //@JsonView({ RestaurantView.Summary.class })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<RestaurantBasicModel> list() {
        return restaurantBasicModelAssembler.toCollectionModel(restaurantService.list());
    }

    //@JsonView({ RestaurantView.IdAndName.class })
    @GetMapping(params = "projection=id-and-name", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<RestaurantIdAndNameModel> listIdAndName() {
        return restaurantIdAndNameModelAssembler.toCollectionModel(restaurantService.list());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantModel find(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.findIfExists(id);
        return restaurantModelAssembler.toModel(restaurant);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantModel save(@RequestBody @Valid RestaurantInput restaurantInput) {
        try {
            Restaurant restaurant = restaurantModelAssembler.toDomainObject(restaurantInput);
            return restaurantModelAssembler.toModel(restaurantService.save(restaurant));
        } catch (KitchenNotFoundException | CityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantModel update(@PathVariable Long id, @RequestBody @Valid RestaurantInput restaurantInput) {

        Restaurant currentRestaurant = restaurantService.findIfExists(id);
        restaurantModelAssembler.copyToDomainObject(restaurantInput, currentRestaurant);
        /*BeanUtils.copyProperties(restaurant, currentRestaurant,
                "id", "createdAt", "paymentWay", "address");*/
        try {
            return restaurantModelAssembler.toModel(restaurantService.save(currentRestaurant));
        } catch (KitchenNotFoundException | CityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{id}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        restaurantService.activate(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/inactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> inactivate(@PathVariable Long id) {
        restaurantService.inactivate(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/activations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void batchActivate(@RequestBody List<Long> restaurantsId) {
        try {
            restaurantService.activate(restaurantsId);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/activations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void batchInactivate(@RequestBody List<Long> restaurantsId) {
        try {
            restaurantService.inactivate(restaurantsId);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}/open")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> toOpen(@PathVariable Long id) {
        restaurantService.toOpen(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/close")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> toClose(@PathVariable Long id) {
        restaurantService.toClose(id);

        return ResponseEntity.noContent().build();
    }

    /*@PatchMapping("/{id}")
    public Restaurant partialUpdate(@PathVariable Long id,
                                    @RequestBody Map<String, Object> fields, HttpServletRequest request) {
        Restaurant currentRestaurant = restaurantService.findIfExists(id);
        merge(fields, currentRestaurant, request);
        validate(currentRestaurant, "restaurant");
        return update(id, currentRestaurant);
    }

    private void validate(Restaurant restaurant, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurant, objectName);
        validator.validate(restaurant, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
    }

    private void merge(Map<String, Object> originFields, Restaurant destinyRestaurant, HttpServletRequest request) {
        ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(request);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurant originRestaurant = objectMapper.convertValue(originFields, Restaurant.class);

            originFields.forEach((propertyName, propertyValue) -> {
                Field field = ReflectionUtils.findField(Restaurant.class, propertyName);
                field.setAccessible(true);
                Object convertedValue = ReflectionUtils.getField(field, originRestaurant);
                ReflectionUtils.setField(field, destinyRestaurant, convertedValue);
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletServerHttpRequest);
        }
    }*/

}
