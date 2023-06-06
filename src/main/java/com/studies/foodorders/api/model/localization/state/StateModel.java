package com.studies.foodorders.api.model.localization.state;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class StateModel {

    private Long id;

    @NotBlank
    private String name;

}
