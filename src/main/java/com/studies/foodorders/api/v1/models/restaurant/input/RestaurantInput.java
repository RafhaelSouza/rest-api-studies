package com.studies.foodorders.api.v1.models.restaurant.input;

import com.studies.foodorders.api.v1.models.kitchen.KitchenIdInput;
import com.studies.foodorders.api.v1.models.localization.address.AddressInput;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(example = "Thai Gourmet", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(example = "5.00", required = true)
    @NotNull
    @PositiveOrZero
    private BigDecimal shippingCosts;

    @Valid
    @NotNull
    private KitchenIdInput kitchen;

    @Valid
    @NotNull
    private AddressInput address;

}
