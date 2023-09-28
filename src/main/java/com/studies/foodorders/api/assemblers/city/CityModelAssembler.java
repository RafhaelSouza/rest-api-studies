package com.studies.foodorders.api.assemblers.city;

import com.studies.foodorders.api.controllers.localization.CityController;
import com.studies.foodorders.api.controllers.localization.StateController;
import com.studies.foodorders.api.model.localization.city.CityInput;
import com.studies.foodorders.api.model.localization.city.CityModel;
import com.studies.foodorders.domain.models.localization.City;
import com.studies.foodorders.domain.models.localization.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CityModelAssembler extends RepresentationModelAssemblerSupport<City, CityModel> {

    @Autowired
    private ModelMapper modelMapper;

    public CityModelAssembler() {
        super(CityController.class, CityModel.class);
    }

    public CityModel toModel(City city) {

        /*CityModel cityModel = modelMapper.map(city, CityModel.class);

        cityModel.add(linkTo(methodOn(CityController.class)
                .find(cityModel.getId())).withSelfRel());*/

        // This approach replaces the implementation above
        CityModel cityModel = createModelWithId(city.getId(), city);

        modelMapper.map(city, cityModel);

        cityModel.add(linkTo(methodOn(CityController.class)
                .list()).withRel("cities"));

        cityModel.getState().add(linkTo(methodOn(StateController.class)
                .find(cityModel.getState().getId())).withSelfRel());


        return cityModel;
    }

    @Override
    public CollectionModel<CityModel> toCollectionModel(Iterable<? extends City> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(CityController.class).withSelfRel());
    }

    public City toDomainObject(CityInput cityInput) {
        return modelMapper.map(cityInput, City.class);
    }

    public void copyToDomainObject(CityInput cityInput, City city) {
        city.setState(new State());
        modelMapper.map(cityInput, city);
    }

}
