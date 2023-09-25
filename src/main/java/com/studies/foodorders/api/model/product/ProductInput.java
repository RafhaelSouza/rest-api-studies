package com.studies.foodorders.api.model.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Setter
@Getter
public class ProductInput {

    @ApiModelProperty(example = "Pork with sweet and sour sauce", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(example = "Delicious pork with special sauce", required = true)
    @NotBlank
    private String description;

    @ApiModelProperty(example = "12.50", required = true)
    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @ApiModelProperty(example = "true", required = true)
    @NotNull
    private Boolean active;

}
