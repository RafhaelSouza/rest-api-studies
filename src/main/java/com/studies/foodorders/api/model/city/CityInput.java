package com.studies.foodorders.api.model.city;

import com.studies.foodorders.api.model.localization.state.StateInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CityInput {

    @NotBlank
    private String name;

    @Valid
    @NotNull
    private StateInput stateInput;

}
