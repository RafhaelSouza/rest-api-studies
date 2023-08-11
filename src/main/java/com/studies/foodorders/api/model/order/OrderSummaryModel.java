package com.studies.foodorders.api.model.order;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.studies.foodorders.api.model.restaurant.RestaurantSummaryModel;
import com.studies.foodorders.api.model.security.user.UserModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@JsonFilter("orderFilter")
@Setter
@Getter
public class OrderSummaryModel {

    private String code;
    private BigDecimal partialPrice;
    private BigDecimal shippingCosts;
    private BigDecimal totalPrice;
    private String status;
    private OffsetDateTime createdAt;
    private RestaurantSummaryModel restaurant;
    private UserModel client;

}
