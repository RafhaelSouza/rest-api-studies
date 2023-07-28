package com.studies.foodorders.api.controllers.restaurant;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studies.foodorders.api.converter.restaurant.RestaurantModelConverter;
import com.studies.foodorders.api.model.restaurant.RestaurantInput;
import com.studies.foodorders.api.model.restaurant.RestaurantModel;
import com.studies.foodorders.core.validation.ValidationException;
import com.studies.foodorders.domain.exceptions.BusinessException;
import com.studies.foodorders.domain.exceptions.CityNotFoundException;
import com.studies.foodorders.domain.exceptions.KitchenNotFoundException;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.services.restaurant.RestaurantService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private SmartValidator validator;

    @Autowired
    private RestaurantModelConverter restaurantModelConverter;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantModel> list() {
        return restaurantModelConverter.toCollectionModel(restaurantService.list());
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
    }*/

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
    }

}
