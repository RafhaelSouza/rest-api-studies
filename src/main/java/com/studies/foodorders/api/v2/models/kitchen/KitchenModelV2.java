package com.studies.foodorders.api.v2.models.kitchen;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "kitchens")
@Setter
@Getter
public class KitchenModelV2 extends RepresentationModel<KitchenModelV2> {

    @ApiModelProperty(example = "1")
    private Long kitchenId;

    @ApiModelProperty(example = "Italian")
    private String kitchenName;

}
