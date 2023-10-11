package com.studies.foodorders.api.v2.assemblers.localization;

import com.studies.foodorders.api.v2.controllers.localization.CityControllerV2;
import com.studies.foodorders.api.v2.links.CityLinksV2;
import com.studies.foodorders.api.v2.models.localization.city.CityInputV2;
import com.studies.foodorders.api.v2.models.localization.city.CityModelV2;
import com.studies.foodorders.domain.models.localization.City;
import com.studies.foodorders.domain.models.localization.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CityModelAssemblerV2 extends RepresentationModelAssemblerSupport<City, CityModelV2> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CityLinksV2 cityLinks;

    public CityModelAssemblerV2() {
        super(CityControllerV2.class, CityModelV2.class);
    }

    public CityModelV2 toModel(City city) {

        CityModelV2 cityModel = createModelWithId(city.getId(), city);

        modelMapper.map(city, cityModel);

        cityModel.add(cityLinks.linkToCities("cities"));

        return cityModel;
    }

    @Override
    public CollectionModel<CityModelV2> toCollectionModel(Iterable<? extends City> entities) {
        return super.toCollectionModel(entities)
                .add(cityLinks.linkToCities());
    }

    public City toDomainObject(CityInputV2 cityInput) {
        return modelMapper.map(cityInput, City.class);
    }

    public void copyToDomainObject(CityInputV2 cityInput, City city) {
        city.setState(new State());
        modelMapper.map(cityInput, city);
    }

}
