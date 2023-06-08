package com.studies.foodorders.api.converter.city;

import com.studies.foodorders.api.model.city.CityInput;
import com.studies.foodorders.api.model.city.CityModel;
import com.studies.foodorders.domain.models.localization.City;
import com.studies.foodorders.domain.models.localization.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityModelConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CityModel toModel(City city) {
        return modelMapper.map(city, CityModel.class);
    }

    public List<CityModel> toCollectionModel(List<City> cities) {

        return cities.stream()
                .map(city -> toModel(city))
                .collect(Collectors.toList());

    }

    public City toDomainObject(CityInput cityInput) {
        return modelMapper.map(cityInput, City.class);
    }

    public void copyToDomainObject(CityInput cityInput, City city) {
        city.setState(new State());
        modelMapper.map(cityInput, city);
    }

}
