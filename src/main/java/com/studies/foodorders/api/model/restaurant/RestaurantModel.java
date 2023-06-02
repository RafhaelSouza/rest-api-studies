package com.studies.foodorders.api.model.restaurant;

import com.studies.foodorders.api.model.kitchen.KitchenModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RestaurantModel {

    private Long id;
    private String name;
    private BigDecimal shippingCosts;
    private KitchenModel kitchen;
    private Boolean active;

}
