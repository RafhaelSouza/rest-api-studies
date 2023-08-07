package com.studies.foodorders.api.model.order;

import com.studies.foodorders.api.model.localization.address.AddressInput;
import com.studies.foodorders.api.model.paymentway.PaymentWayIdInput;
import com.studies.foodorders.api.model.restaurant.RestaurantIdInput;
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
