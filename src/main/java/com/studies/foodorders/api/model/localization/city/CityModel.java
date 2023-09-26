package com.studies.foodorders.api.model.localization.city;

import com.studies.foodorders.api.model.localization.state.StateModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "City", description = "City representation")
@Setter
@Getter
public class CityModel {

    @ApiModelProperty(value = "City id", example = "1")
    private Long id;

    @ApiModelProperty(example = "Greenfield")
    private String name;

    private StateModel stateModel;

}
