package com.studies.foodorders.api.v1.models.restaurant.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class RestaurantIdInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;

}
