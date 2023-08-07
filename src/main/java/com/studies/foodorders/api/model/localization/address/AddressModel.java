package com.studies.foodorders.api.model.localization.address;

import com.studies.foodorders.api.model.localization.city.CitySummaryModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressModel {

    private String postalCode;
    private String street;
    private String number;
    private String complement;
    private String district;
    private CitySummaryModel city;

}
