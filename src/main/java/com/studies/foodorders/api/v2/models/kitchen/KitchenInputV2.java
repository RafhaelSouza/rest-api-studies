package com.studies.foodorders.api.v2.models.kitchen;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class KitchenInputV2 {

    @ApiModelProperty(example = "Italian", required = true)
    @NotBlank
    private String kitchenName;

}
