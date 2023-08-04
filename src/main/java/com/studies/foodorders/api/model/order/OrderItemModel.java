package com.studies.foodorders.api.model.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemModel {

    private Long productId;
    private String productName;
    private Integer amount;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String observations;

}
