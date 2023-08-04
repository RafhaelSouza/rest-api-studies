package com.studies.foodorders.api.model.order;

import com.studies.foodorders.api.model.localization.address.AddressModel;
import com.studies.foodorders.api.model.paymentway.PaymentWayModel;
import com.studies.foodorders.api.model.restaurant.RestaurantSummaryModel;
import com.studies.foodorders.api.model.security.user.UserModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Setter
@Getter
public class OrderModel {

    private Long id;
    private BigDecimal partialPrice;
    private BigDecimal shippingCosts;
    private BigDecimal totalPrice;
    private String status;
    private OffsetDateTime createdAt;
    private OffsetDateTime confirmedAt;
    private OffsetDateTime deliveredAt;
    private OffsetDateTime canceledAt;
    private RestaurantSummaryModel restaurant;
    private UserModel client;
    private PaymentWayModel paymentWay;
    private AddressModel deliveryAddress;
    private List<OrderItemModel> items;

}
