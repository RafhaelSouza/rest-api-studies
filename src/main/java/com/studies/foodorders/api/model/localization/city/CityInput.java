package com.studies.foodorders.api.model.localization.city;

import com.studies.foodorders.api.model.localization.state.StateInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CityInput {

    @ApiModelProperty(example = "Indiana")
    @NotBlank
    private String name;

    @Valid
    @NotNull
    private StateInput stateInput;

}
