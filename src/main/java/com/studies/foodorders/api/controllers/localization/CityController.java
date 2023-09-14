package com.studies.foodorders.api.controllers.localization;

import com.studies.foodorders.api.converter.city.CityModelConverter;
import com.studies.foodorders.api.model.localization.city.CityInput;
import com.studies.foodorders.api.model.localization.city.CityModel;
import com.studies.foodorders.domain.exceptions.BusinessException;
import com.studies.foodorders.domain.exceptions.StateNotFoundException;
import com.studies.foodorders.domain.models.localization.City;
import com.studies.foodorders.domain.services.localization.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cities")
@RestController
@RequestMapping(value = "/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityModelConverter cityModelConverter;

    @ApiOperation("List of cities")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CityModel> list() {
        return cityModelConverter.toCollectionModel(cityService.list());
    }

    @ApiOperation("Find a city by id")
    @GetMapping("/{id}")
    public CityModel find(
            @ApiParam(value = "City id", example = "1")
            @PathVariable Long id) {
        return cityModelConverter.toModel(cityService.findIfExists(id));
    }

    @ApiOperation("Register a city")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityModel save(
            @ApiParam(name = "Request body", value = "Representation of a new city")
            @RequestBody @Valid CityInput cityInput) {
        try {
            City city = cityModelConverter.toDomainObject(cityInput);
            return cityModelConverter.toModel(cityService.save(city));
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @ApiOperation("Update a city")
    @PutMapping("/{id}")
    public CityModel update(
            @ApiParam(value = "City id", example = "1")
            @PathVariable Long id,

            @ApiParam(name = "Request Body", value = "Representation of a city with the new values")
            @RequestBody @Valid CityInput cityInput) {
        try {
            City currentCity = cityService.findIfExists(id);
            cityModelConverter.copyToDomainObject(cityInput, currentCity);
            return cityModelConverter.toModel(cityService.save(currentCity));
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @ApiOperation("Delete a city")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @ApiParam(value = "City id", example = "1")
            @PathVariable Long id) {
        cityService.delete(id);
    }

}
