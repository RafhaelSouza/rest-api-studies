package com.studies.foodorders.api.v1.models.localization.city;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cities")
@Setter
@Getter
public class CitySummaryModel extends RepresentationModel<CitySummaryModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "San francisco")
    private String name;

    @ApiModelProperty(example = "California")
    private String state;

}
