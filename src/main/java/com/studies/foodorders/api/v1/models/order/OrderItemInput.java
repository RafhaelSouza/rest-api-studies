package com.studies.foodorders.api.v1.models.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Setter
@Getter
public class OrderItemInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long productId;

    @ApiModelProperty(example = "2", required = true)
    @NotNull
    @PositiveOrZero
    private Integer amount;

    @ApiModelProperty(example = "Well done steak")
    private String observations;

}
