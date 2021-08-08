package com.studies.foodorders.api.controllers.localization;

import com.studies.foodorders.api.converter.city.CityInputDisconverter;
import com.studies.foodorders.api.converter.city.CityModelConverter;
import com.studies.foodorders.api.model.city.CityInput;
import com.studies.foodorders.api.model.city.CityModel;
import com.studies.foodorders.domain.exceptions.BusinessException;
import com.studies.foodorders.domain.exceptions.StateNotFoundException;
import com.studies.foodorders.domain.models.localization.City;
import com.studies.foodorders.domain.services.localization.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityModelConverter cityModelConverter;

    @Autowired
    private CityInputDisconverter cityInputDisconverter;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CityModel> list() {
        return cityModelConverter.toCollectionModel(cityService.list());
    }

    @GetMapping("/{id}")
    public CityModel find(@PathVariable Long id) {
        return cityModelConverter.toModel(cityService.findIfExists(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityModel save(@RequestBody @Valid CityInput cityInput) {
        try {
            City city = cityInputDisconverter.toDomainObject(cityInput);
            return cityModelConverter.toModel(cityService.save(city));
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public CityModel update(@PathVariable Long id, @RequestBody @Valid CityInput cityInput) {
        try {
            City currentCity = cityService.findIfExists(id);
            cityInputDisconverter.copyToDomainObject(cityInput, currentCity);
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
