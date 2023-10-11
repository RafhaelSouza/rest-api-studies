package com.studies.foodorders.api.v2.controllers.localization;

import com.studies.foodorders.api.helpers.ResourceUriHelper;
import com.studies.foodorders.api.v2.assemblers.localization.CityModelAssemblerV2;
import com.studies.foodorders.api.v2.models.localization.city.CityInputV2;
import com.studies.foodorders.api.v2.models.localization.city.CityModelV2;
import com.studies.foodorders.domain.exceptions.BusinessException;
import com.studies.foodorders.domain.exceptions.StateNotFoundException;
import com.studies.foodorders.domain.models.localization.City;
import com.studies.foodorders.domain.services.localization.CityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Cities")
@RestController
@RequestMapping(value = "/v2/cities")
public class CityControllerV2 {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityModelAssemblerV2 cityModelAssembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<CityModelV2> list() {
        return cityModelAssembler.toCollectionModel(cityService.list());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CityModelV2 find(@PathVariable Long id) {
        return cityModelAssembler.toModel(cityService.findIfExists(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CityModelV2 save(@RequestBody @Valid CityInputV2 cityInput) {
        try {
            City city = cityModelAssembler.toDomainObject(cityInput);
            CityModelV2 cityModel = cityModelAssembler.toModel(cityService.save(city));

            ResourceUriHelper.addUriInResponseHeader(cityModel.getCityId());

            return cityModel;
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CityModelV2 update(@PathVariable Long id, @RequestBody @Valid CityInputV2 cityInput) {
        try {
            City currentCity = cityService.findIfExists(id);
            cityModelAssembler.copyToDomainObject(cityInput, currentCity);
            return cityModelAssembler.toModel(cityService.save(currentCity));
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
