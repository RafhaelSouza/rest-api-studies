package com.studies.foodorders.api.converter.restaurant;

import com.studies.foodorders.api.model.restaurant.RestaurantInput;
import com.studies.foodorders.domain.models.kitchen.Kitchen;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantInputDisconverter {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurant toDomainObject(RestaurantInput restaurantInput) {
        return modelMapper.map(restaurantInput, Restaurant.class);
    }

    public void copyToDomainObject(RestaurantInput restaurantInput, Restaurant restaurant) {
        restaurant.setKitchen(new Kitchen());
        modelMapper.map(restaurantInput, restaurant);
    }

}
