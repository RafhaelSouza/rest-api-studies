package com.studies.foodorders.api.v1.models.order;

import com.studies.foodorders.api.v1.models.localization.address.AddressInput;
import com.studies.foodorders.api.v1.models.paymentway.PaymentWayIdInput;
import com.studies.foodorders.api.v1.models.restaurant.input.RestaurantIdInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
public class OrderInput {

    @Valid
    @NotNull
    private RestaurantIdInput restaurant;

    @Valid
    @NotNull
    private AddressInput deliveryAddress;

    @Valid
    @NotNull
    private PaymentWayIdInput paymentWay;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<OrderItemInput> items;

}
