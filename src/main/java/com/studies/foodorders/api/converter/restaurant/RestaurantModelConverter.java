package com.studies.foodorders.api.converter.restaurant;

import com.studies.foodorders.api.model.kitchen.KitchenModel;
import com.studies.foodorders.api.model.restaurant.RestaurantModel;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantModelConverter {

    @Autowired
    private ModelMapper modelMapper;

    public RestaurantModel toModel(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantModel.class);
    }

    public List<RestaurantModel> toCollectionModel(List<Restaurant> restaurants) {

        return restaurants.stream()
                .map(restaurant -> toModel(restaurant))
                .collect(Collectors.toList());

    }

}
