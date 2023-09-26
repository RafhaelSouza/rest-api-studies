package com.studies.foodorders.api.model.localization.state;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "State", description = "State representation")
@Setter
@Getter
public class StateModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Indiana")
    @NotBlank
    private String name;

}
