package com.studies.foodorders.api.v1.controllers.restaurant;

import com.studies.foodorders.api.v1.assemblers.restaurant.RestaurantBasicModelAssembler;
import com.studies.foodorders.api.v1.assemblers.restaurant.RestaurantIdAndNameModelAssembler;
import com.studies.foodorders.api.v1.assemblers.restaurant.RestaurantModelAssembler;
import com.studies.foodorders.api.v1.models.restaurant.RestaurantBasicModel;
import com.studies.foodorders.api.v1.models.restaurant.RestaurantIdAndNameModel;
import com.studies.foodorders.api.v1.models.restaurant.RestaurantModel;
import com.studies.foodorders.api.v1.models.restaurant.input.RestaurantInput;
import com.studies.foodorders.api.v1.openapi.controllers.RestaurantControllerOpenApi;
import com.studies.foodorders.core.security.CheckSecurity;
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
@RequestMapping("/v1/restaurants")
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
    @CheckSecurity.Restaurants.AllowToSearch
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<RestaurantBasicModel> list() {
        return restaurantBasicModelAssembler.toCollectionModel(restaurantService.list());
    }

    //@JsonView({ RestaurantView.IdAndName.class })
    @CheckSecurity.Restaurants.AllowToSearch
    @GetMapping(params = "projection=id-and-name", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<RestaurantIdAndNameModel> listIdAndName() {
        return restaurantIdAndNameModelAssembler.toCollectionModel(restaurantService.list());
    }

    @CheckSecurity.Restaurants.AllowToSearch
    @GetMapping(path = "/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantModel find(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findIfExists(restaurantId);
        return restaurantModelAssembler.toModel(restaurant);
    }

    @CheckSecurity.Restaurants.AllowToManageRestaurant
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

    @CheckSecurity.Restaurants.AllowToManageRestaurant
    @PutMapping(path = "/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantModel update(@PathVariable Long restaurantId, @RequestBody @Valid RestaurantInput restaurantInput) {

        Restaurant currentRestaurant = restaurantService.findIfExists(restaurantId);
        restaurantModelAssembler.copyToDomainObject(restaurantInput, currentRestaurant);
        /*BeanUtils.copyProperties(restaurant, currentRestaurant,
                "id", "createdAt", "paymentWay", "address");*/
        try {
            return restaurantModelAssembler.toModel(restaurantService.save(currentRestaurant));
        } catch (KitchenNotFoundException | CityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @CheckSecurity.Restaurants.AllowToManageRestaurant
    @PutMapping("/{restaurantId}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> activate(@PathVariable Long restaurantId) {
        restaurantService.activate(restaurantId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.AllowToManageRestaurant
    @DeleteMapping("/{restaurantId}/inactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> inactivate(@PathVariable Long restaurantId) {
        restaurantService.inactivate(restaurantId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.AllowToManageRestaurant
    @PutMapping("/activations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void batchActivate(@RequestBody List<Long> restaurantsId) {
        try {
            restaurantService.activate(restaurantsId);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @CheckSecurity.Restaurants.AllowToManageRestaurant
    @DeleteMapping("/activations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void batchInactivate(@RequestBody List<Long> restaurantsId) {
        try {
            restaurantService.inactivate(restaurantsId);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @CheckSecurity.Restaurants.AllowToManageRestaurantOperation
    @PutMapping("/{restaurantId}/open")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> toOpen(@PathVariable Long restaurantId) {
        restaurantService.toOpen(restaurantId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.AllowToManageRestaurantOperation
    @DeleteMapping("/{restaurantId}/close")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> toClose(@PathVariable Long restaurantId) {
        restaurantService.toClose(restaurantId);

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
