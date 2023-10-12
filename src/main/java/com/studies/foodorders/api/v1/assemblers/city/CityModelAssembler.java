package com.studies.foodorders.api.v1.assemblers.city;

import com.studies.foodorders.api.v1.controllers.localization.CityController;
import com.studies.foodorders.api.v1.links.CityLinks;
import com.studies.foodorders.api.v1.links.StateLinks;
import com.studies.foodorders.api.v1.models.localization.city.CityInput;
import com.studies.foodorders.api.v1.models.localization.city.CityModel;
import com.studies.foodorders.domain.models.localization.City;
import com.studies.foodorders.domain.models.localization.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CityModelAssembler extends RepresentationModelAssemblerSupport<City, CityModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CityLinks cityLinks;

    @Autowired
    private StateLinks stateLinks;

    public CityModelAssembler() {
        super(CityController.class, CityModel.class);
    }

    public CityModel toModel(City city) {

        CityModel cityModel = createModelWithId(city.getId(), city);

        modelMapper.map(city, cityModel);

        cityModel.add(cityLinks.linkToCities("cities"));

        cityModel.getState().add(stateLinks.linkToState(cityModel.getState().getId()));

        return cityModel;
    }

    @Override
    public CollectionModel<CityModel> toCollectionModel(Iterable<? extends City> entities) {
        return super.toCollectionModel(entities)
                .add(cityLinks.linkToCities());
    }

    public City toDomainObject(CityInput cityInput) {
        return modelMapper.map(cityInput, City.class);
    }

    public void copyToDomainObject(CityInput cityInput, City city) {
        city.setState(new State());
        modelMapper.map(cityInput, city);
    }

}
