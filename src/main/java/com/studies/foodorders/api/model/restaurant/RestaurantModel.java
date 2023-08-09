package com.studies.foodorders.api.model.restaurant;

import com.fasterxml.jackson.annotation.JsonView;
import com.studies.foodorders.api.model.kitchen.KitchenModel;
import com.studies.foodorders.api.model.localization.address.AddressModel;
import com.studies.foodorders.api.model.restaurant.view.RestaurantView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RestaurantModel {

    @JsonView({ RestaurantView.Summary.class, RestaurantView.IdAndName.class })
    private Long id;

    @JsonView({ RestaurantView.Summary.class, RestaurantView.IdAndName.class })
    private String name;

    @JsonView({ RestaurantView.Summary.class, RestaurantView.IdAndName.class })
    private BigDecimal shippingCosts;

    @JsonView({ RestaurantView.Summary.class, RestaurantView.IdAndName.class })
    private KitchenModel kitchen;

    private Boolean active;
    private Boolean opened;
    private AddressModel address;

}
