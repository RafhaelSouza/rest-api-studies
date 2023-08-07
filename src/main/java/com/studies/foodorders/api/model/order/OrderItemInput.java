package com.studies.foodorders.api.model.order;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Setter
@Getter
public class OrderItemInput {

    @NotNull
    private Long productId;

    @NotNull
    @PositiveOrZero
    private Integer amount;

    private String observations;

}
