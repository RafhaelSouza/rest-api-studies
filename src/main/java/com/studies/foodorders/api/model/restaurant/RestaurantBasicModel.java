package com.studies.foodorders.api.model.restaurant;

import com.studies.foodorders.api.model.kitchen.KitchenModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurants")
@Setter
@Getter
public class RestaurantBasicModel extends RepresentationModel<RestaurantBasicModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
    private String name;

    @ApiModelProperty(example = "21.00")
    private BigDecimal shippingCosts;

    private KitchenModel kitchen;

}
