package com.studies.foodorders.api.model.restaurant;

import com.studies.foodorders.api.model.kitchen.KitchenIdInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Setter
@Getter
public class RestaurantInput {

    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private BigDecimal shippingCosts;

    @Valid
    @NotNull
    private KitchenIdInput kitchen;

}
