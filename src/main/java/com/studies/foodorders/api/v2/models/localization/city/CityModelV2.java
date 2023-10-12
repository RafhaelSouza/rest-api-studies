package com.studies.foodorders.api.v2.models.localization.city;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cities")
@ApiModel(value = "City", description = "City representation")
@Setter
@Getter
public class CityModelV2 extends RepresentationModel<CityModelV2> {

    @ApiModelProperty(value = "City id", example = "1")
    private Long cityId;

    @ApiModelProperty(example = "Los Angeles")
    private String cityName;

    private Long stateId;
    private String stateName;

}
