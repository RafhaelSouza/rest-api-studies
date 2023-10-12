package com.studies.foodorders.api.v1.models.localization.address;

import com.studies.foodorders.api.v1.models.localization.city.CitySummaryModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressModel {

    @ApiModelProperty(example = "11111-111")
    private String postalCode;

    @ApiModelProperty(example = "First Avenue", required = true)
    private String street;

    @ApiModelProperty(example = "100", required = true)
    private String number;

    @ApiModelProperty(example = "Apt 101")
    private String complement;

    @ApiModelProperty(example = "Downtown", required = true)
    private String district;

    private CitySummaryModel city;

}
