package com.studies.foodorders.api.model.order;

import com.studies.foodorders.api.model.localization.address.AddressModel;
import com.studies.foodorders.api.model.paymentway.PaymentWayModel;
import com.studies.foodorders.api.model.restaurant.RestaurantIdAndNameModel;
import com.studies.foodorders.api.model.security.user.UserModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Relation(collectionRelation = "orders")
@Setter
@Getter
public class OrderModel extends RepresentationModel<OrderModel> {

    @ApiModelProperty(example = "cee92b85-1456-43d7-842f-93be0d57b954")
    private String code;

    @ApiModelProperty(example = "298.90")
    private BigDecimal partialPrice;

    @ApiModelProperty(example = "10.00")
    private BigDecimal shippingCosts;

    @ApiModelProperty(example = "308.90")
    private BigDecimal totalPrice;

    @ApiModelProperty(example = "CREATED")
    private String status;

    @ApiModelProperty(example = "2023-09-20T08:57:04Z")
    private OffsetDateTime createdAt;

    @ApiModelProperty(example = "2023-09-20T08:58:04Z")
    private OffsetDateTime confirmedAt;

    @ApiModelProperty(example = "2023-09-20T09:57:04Z")
    private OffsetDateTime deliveredAt;

    @ApiModelProperty(example = "2023-09-20T09:57:04Z")
    private OffsetDateTime canceledAt;

    private RestaurantIdAndNameModel restaurant;
    private UserModel client;
    private PaymentWayModel paymentWay;
    private AddressModel deliveryAddress;
    private List<OrderItemModel> items;

}
