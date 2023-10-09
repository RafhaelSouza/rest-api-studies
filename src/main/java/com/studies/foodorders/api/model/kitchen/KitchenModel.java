package com.studies.foodorders.api.model.kitchen;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "kitchens")
@Setter
@Getter
public class KitchenModel extends RepresentationModel<KitchenModel> {

    @ApiModelProperty(example = "1")
    //@JsonView({ RestaurantView.Summary.class })
    private Long id;

    @ApiModelProperty(example = "Italian")
    //@JsonView({ RestaurantView.Summary.class })
    private String name;

}
