package com.studies.foodorders.api.openapi.models;

import com.studies.foodorders.api.model.kitchen.KitchenModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@ApiModel("RestaurantBasicModel")
@Setter
@Getter
public class RestaurantBasicModelOpenApi {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
    private String name;

    @ApiModelProperty(example = "21.00")
    private BigDecimal shippingCosts;

    private KitchenModel kitchen;

}
