package com.studies.foodorders.api.v1.models.order;

import com.studies.foodorders.api.v1.models.restaurant.RestaurantIdAndNameModel;
import com.studies.foodorders.api.v1.models.security.user.UserModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Relation(collectionRelation = "orders")
@Setter
@Getter
public class OrderSummaryModel extends RepresentationModel<OrderSummaryModel> {

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

    private RestaurantIdAndNameModel restaurant;
    private UserModel client;

}
