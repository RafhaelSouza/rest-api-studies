package com.studies.foodorders.api.v2.models.localization.city;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CityInputV2 {

    @ApiModelProperty(example = "San Francisco", required = true)
    @NotBlank
    private String cityName;

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long stateId;

}
