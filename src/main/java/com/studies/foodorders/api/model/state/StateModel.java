package com.studies.foodorders.api.model.state;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class StateModel {

    @NotBlank
    private String name;

}
