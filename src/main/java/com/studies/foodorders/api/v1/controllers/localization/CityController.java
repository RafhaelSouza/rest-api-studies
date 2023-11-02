package com.studies.foodorders.api.v1.controllers.localization;

import com.studies.foodorders.api.helpers.ResourceUriHelper;
import com.studies.foodorders.api.v1.assemblers.city.CityModelAssembler;
import com.studies.foodorders.api.v1.models.localization.city.CityInput;
import com.studies.foodorders.api.v1.models.localization.city.CityModel;
import com.studies.foodorders.api.v1.openapi.controllers.CityControllerOpenApi;
import com.studies.foodorders.core.security.CheckSecurity;
import com.studies.foodorders.domain.exceptions.BusinessException;
import com.studies.foodorders.domain.exceptions.StateNotFoundException;
import com.studies.foodorders.domain.models.localization.City;
import com.studies.foodorders.domain.services.localization.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/cities")
public class CityController implements CityControllerOpenApi {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityModelAssembler cityModelAssembler;

    @Deprecated
    @CheckSecurity.Cities.AllowToSearch
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<CityModel> list() {
        return cityModelAssembler.toCollectionModel(cityService.list());
    }

    @Deprecated
    @CheckSecurity.Cities.AllowToSearch
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CityModel find(@PathVariable Long id) {
        return cityModelAssembler.toModel(cityService.findIfExists(id));
    }

    @Deprecated
    @CheckSecurity.Cities.AllowToUpdate
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CityModel save(@RequestBody @Valid CityInput cityInput) {
        try {
            City city = cityModelAssembler.toDomainObject(cityInput);
            CityModel cityModel = cityModelAssembler.toModel(cityService.save(city));

            ResourceUriHelper.addUriInResponseHeader(cityModel.getId());

            return cityModel;
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Deprecated
    @CheckSecurity.Cities.AllowToUpdate
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CityModel update(@PathVariable Long id, @RequestBody @Valid CityInput cityInput) {
        try {
            City currentCity = cityService.findIfExists(id);
            cityModelAssembler.copyToDomainObject(cityInput, currentCity);
            return cityModelAssembler.toModel(cityService.save(currentCity));
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Deprecated
    @CheckSecurity.Cities.AllowToUpdate
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cityService.delete(id);
    }

}
