package com.studies.foodorders.api.model.localization.address;

import com.studies.foodorders.api.model.localization.state.StateModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CityModel {

    private Long id;
    private String name;
    private StateModel state;

}
