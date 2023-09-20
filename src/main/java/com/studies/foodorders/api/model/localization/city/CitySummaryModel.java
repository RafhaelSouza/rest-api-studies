package com.studies.foodorders.api.model.localization.city;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CitySummaryModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "San francisco")
    private String name;

    @ApiModelProperty(example = "California")
    private String state;

}
