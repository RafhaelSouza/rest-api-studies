package com.studies.foodorders.api.model.localization.state;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class StateInput {

    @ApiModelProperty(example = "1")
    @NotNull
    private Long id;

}
