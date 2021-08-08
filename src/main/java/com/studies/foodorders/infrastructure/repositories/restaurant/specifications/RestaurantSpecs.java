package com.studies.foodorders.infrastructure.repositories.restaurant.specifications;

import com.studies.foodorders.domain.models.restaurant.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs {

    public static Specification<Restaurant> withFreeShipping() {
        return (root, query, builder) ->
                builder.equal(root.get("shippingCosts"), BigDecimal.ZERO);
    }

    public static Specification<Restaurant> withSimilarName(String name) {
        return (root, query, builder) ->
                builder.like(root.get("name"), "%" + name + "%");
    }

}
