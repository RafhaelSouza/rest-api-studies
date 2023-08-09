package com.studies.foodorders.api.model.kitchen;

import com.fasterxml.jackson.annotation.JsonView;
import com.studies.foodorders.api.model.restaurant.view.RestaurantView;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KitchenModel {

    @JsonView({ RestaurantView.Summary.class })
    private Long id;

    @JsonView({ RestaurantView.Summary.class })
    private String name;

}
