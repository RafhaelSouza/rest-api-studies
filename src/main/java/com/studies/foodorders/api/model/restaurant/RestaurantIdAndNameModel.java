package com.studies.foodorders.api.model.restaurant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "restaurants")
@Setter
@Getter
public class RestaurantIdAndNameModel extends RepresentationModel<RestaurantIdAndNameModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Java Steakhouse")
    private String name;

}
