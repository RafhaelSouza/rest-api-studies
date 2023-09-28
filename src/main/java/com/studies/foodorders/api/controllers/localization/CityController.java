package com.studies.foodorders.api.controllers.localization;

import com.studies.foodorders.api.converter.city.CityModelConverter;
import com.studies.foodorders.api.helpers.ResourceUriHelper;
import com.studies.foodorders.api.model.localization.city.CityInput;
import com.studies.foodorders.api.model.localization.city.CityModel;
import com.studies.foodorders.api.openapi.controllers.CityControllerOpenApi;
import com.studies.foodorders.domain.exceptions.BusinessException;
import com.studies.foodorders.domain.exceptions.StateNotFoundException;
import com.studies.foodorders.domain.models.localization.City;
import com.studies.foodorders.domain.services.localization.CityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = "Cities")
@RestController
@RequestMapping(value = "/cities")
public class CityController implements CityControllerOpenApi {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityModelConverter cityModelConverter;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CityModel> list() {
        return cityModelConverter.toCollectionModel(cityService.list());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CityModel find(@PathVariable Long id) {
        CityModel cityModel = cityModelConverter.toModel(cityService.findIfExists(id));

        cityModel.add(linkTo(methodOn(CityController.class)
                .find(cityModel.getId())).withSelfRel());

        cityModel.add(linkTo(methodOn(CityController.class)
                .list()).withRel("cities"));

        cityModel.getState().add(linkTo(methodOn(StateController.class)
                .find(cityModel.getState().getId())).withSelfRel());

        return cityModel;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CityModel save(@RequestBody @Valid CityInput cityInput) {
        try {
            City city = cityModelConverter.toDomainObject(cityInput);
            CityModel cityModel = cityModelConverter.toModel(cityService.save(city));

            ResourceUriHelper.addUriInResponseHeader(cityModel.getId());

            return cityModel;
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CityModel update(@PathVariable Long id, @RequestBody @Valid CityInput cityInput) {
        try {
            City currentCity = cityService.findIfExists(id);
            cityModelConverter.copyToDomainObject(cityInput, currentCity);
            return cityModelConverter.toModel(cityService.save(currentCity));
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cityService.delete(id);
    }

}
