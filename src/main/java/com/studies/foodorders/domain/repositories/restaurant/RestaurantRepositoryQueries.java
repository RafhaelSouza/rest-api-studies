package com.studies.foodorders.domain.repositories.restaurant;

import com.studies.foodorders.domain.models.restaurant.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQueries {
    List<Restaurant> find(String name, BigDecimal initialCost, BigDecimal finalCost);
    List<Restaurant> findWithFreeShipping(String name);
}
