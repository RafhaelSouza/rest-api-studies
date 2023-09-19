package com.studies.foodorders.api.model.kitchen;

import com.fasterxml.jackson.annotation.JsonView;
import com.studies.foodorders.api.model.restaurant.view.RestaurantView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KitchenModel {

    @ApiModelProperty(example = "1")
    @JsonView({ RestaurantView.Summary.class })
    private Long id;

    @ApiModelProperty(example = "Italian")
    @JsonView({ RestaurantView.Summary.class })
    private String name;

}
