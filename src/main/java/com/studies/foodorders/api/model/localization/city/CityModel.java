package com.studies.foodorders.api.model.localization.city;

import com.studies.foodorders.api.model.localization.state.StateModel;
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
public class CityModel extends RepresentationModel<CityModel> {

    @ApiModelProperty(value = "City id", example = "1")
    private Long id;

    @ApiModelProperty(example = "Los Angeles")
    private String name;

    private StateModel state;

}
