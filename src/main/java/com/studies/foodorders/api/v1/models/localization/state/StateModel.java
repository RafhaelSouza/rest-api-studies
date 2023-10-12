package com.studies.foodorders.api.v1.models.localization.state;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "states")
@ApiModel(value = "State", description = "State representation")
@Setter
@Getter
public class StateModel extends RepresentationModel<StateModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "California")
    private String name;

}
