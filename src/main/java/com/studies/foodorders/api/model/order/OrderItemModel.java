package com.studies.foodorders.api.model.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemModel extends RepresentationModel<OrderItemModel> {

    @ApiModelProperty(example = "1")
    private Long productId;

    @ApiModelProperty(example = "Ancho steak")
    private String productName;

    @ApiModelProperty(example = "2")
    private Integer amount;

    @ApiModelProperty(example = "88.70")
    private BigDecimal unitPrice;

    @ApiModelProperty(example = "177.40")
    private BigDecimal totalPrice;

    @ApiModelProperty(example = "Well done steak")
    private String observations;

}
