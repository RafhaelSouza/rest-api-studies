package com.studies.foodorders.api.model.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Pork with sweet and sour sauce", required = true)
    private String name;

    @ApiModelProperty(example = "Delicious pork with special sauce", required = true)
    private String description;

    @ApiModelProperty(example = "12.50", required = true)
    private BigDecimal price;

    @ApiModelProperty(example = "true", required = true)
    private Boolean active;

}
