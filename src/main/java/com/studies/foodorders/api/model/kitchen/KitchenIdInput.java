package com.studies.foodorders.api.model.kitchen;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class KitchenIdInput {

    @NotNull
    private Long id;

}
