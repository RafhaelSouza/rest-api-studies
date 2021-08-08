package com.studies.foodorders.api.model.kitchen;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class KitchenInput {

    @NotBlank
    private String name;

}
