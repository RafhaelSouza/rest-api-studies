package com.studies.foodorders.api.controllers.restaurant;

import com.fasterxml.jackson.annotation.JsonView;
import com.studies.foodorders.api.converter.restaurant.RestaurantModelConverter;
import com.studies.foodorders.api.model.restaurant.RestaurantModel;
import com.studies.foodorders.api.model.restaurant.input.RestaurantInput;
import com.studies.foodorders.api.model.restaurant.view.RestaurantView;
import com.studies.foodorders.domain.exceptions.BusinessException;
import com.studies.foodorders.domain.exceptions.CityNotFoundException;
import com.studies.foodorders.domain.exceptions.KitchenNotFoundException;
import com.studies.foodorders.domain.exceptions.RestaurantNotFoundException;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.services.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private SmartValidator validator;

    @Autowired
    private RestaurantModelConverter restaurantModelConverter;

    @GetMapping
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
    }

    @JsonView({ RestaurantView.Summary.class })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantModel> list() {

        return restaurantModelConverter.toCollectionModel(restaurantService.list());
    }

    @JsonView({ RestaurantView.IdAndName.class })
    @GetMapping(params = "projection=id-and-name")
    public List<RestaurantModel> listIdAndName() {
        return list();
    }

    @GetMapping("/{id}")
    public RestaurantModel find(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.findIfExists(id);
        return restaurantModelConverter.toModel(restaurant);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantModel save(@RequestBody @Valid RestaurantInput restaurantInput) {
        try {
            Restaurant restaurant = restaurantModelConverter.toDomainObject(restaurantInput);
            return restaurantModelConverter.toModel(restaurantService.save(restaurant));
        } catch (KitchenNotFoundException | CityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestaurantModel update(@PathVariable Long id, @RequestBody @Valid RestaurantInput restaurantInput) {

        Restaurant currentRestaurant = restaurantService.findIfExists(id);
        restaurantModelConverter.copyToDomainObject(restaurantInput, currentRestaurant);
        /*BeanUtils.copyProperties(restaurant, currentRestaurant,
                "id", "createdAt", "paymentWay", "address");*/
        try {
            return restaurantModelConverter.toModel(restaurantService.save(currentRestaurant));
        } catch (KitchenNotFoundException | CityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{id}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long id) {
        restaurantService.activate(id);
    }

    @DeleteMapping("/{id}/inactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivate(@PathVariable Long id) {
        restaurantService.inactivate(id);
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
    public void toOpen(@PathVariable Long id) {
        restaurantService.toOpen(id);
    }

    @DeleteMapping("/{id}/close")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void toClose(@PathVariable Long id) {
        restaurantService.toClose(id);
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
