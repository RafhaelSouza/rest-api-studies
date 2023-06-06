package com.studies.foodorders.api.model.city;

import com.studies.foodorders.api.model.localization.state.StateInput;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CityModel {

    private Long id;
    private String name;
    private StateInput stateInput;

}
