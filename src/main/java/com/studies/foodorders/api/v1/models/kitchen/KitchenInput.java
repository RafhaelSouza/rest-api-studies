package com.studies.foodorders.api.v1.models.kitchen;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class KitchenInput {

    @ApiModelProperty(example = "Italian", required = true)
    @NotBlank
    private String name;

}
