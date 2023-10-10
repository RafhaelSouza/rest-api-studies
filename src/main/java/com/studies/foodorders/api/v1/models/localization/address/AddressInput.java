package com.studies.foodorders.api.v1.models.localization.address;

import com.studies.foodorders.api.v1.models.localization.city.CityIdInput;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AddressInput {

    @ApiModelProperty(example = "11111-111", required = true)
    @NotBlank
    private String postalCode;

    @ApiModelProperty(example = "First Avenue", required = true)
    @NotBlank
    private String street;

    @ApiModelProperty(example = "100", required = true)
    @NotBlank
    private String number;

    @ApiModelProperty(example = "Apt 101")
    private String complement;

    @ApiModelProperty(example = "Downtown", required = true)
    @NotBlank
    private String district;

    @Valid
    @NotNull
    private CityIdInput city;

}
