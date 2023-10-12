package com.studies.foodorders.api.v1.models.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "products")
@Setter
@Getter
public class ProductModel extends RepresentationModel<ProductModel> {

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
