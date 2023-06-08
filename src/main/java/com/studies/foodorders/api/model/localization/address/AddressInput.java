package com.studies.foodorders.api.model.localization.address;

import com.studies.foodorders.api.model.localization.city.CityIdInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AddressInput {

    @NotBlank
    private String postalCode;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    private String complement;

    @NotBlank
    private String district;

    @Valid
    @NotNull
    private CityIdInput city;

}
