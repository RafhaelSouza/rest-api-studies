package com.studies.foodorders.domain.services.localization;

import com.studies.foodorders.domain.exceptions.CityNotFoundException;
import com.studies.foodorders.domain.exceptions.StateNotFoundException;
import com.studies.foodorders.domain.exceptions.UsedEntityException;
import com.studies.foodorders.domain.models.localization.City;
import com.studies.foodorders.domain.models.localization.State;
import com.studies.foodorders.domain.repositories.localization.CityRepository;
import com.studies.foodorders.domain.repositories.localization.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    public static final String CITY_IN_USE = "City in use.";

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public List<City> list() {
        return cityRepository.findAll();
    }

    public Optional<City> find(Long id) {
        Optional<City> city = cityRepository.findById(id);
        return city;
    }

    @Transactional
    public City save(City city) {
        Long stateId = city.getState().getId();
        State state = stateRepository.findById(stateId)
                .orElseThrow(() -> new StateNotFoundException(stateId));
        city.setState(state);
        return cityRepository.save(city);
    }

    @Transactional
    public void delete(Long id) {
        try {
            cityRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new CityNotFoundException(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new UsedEntityException(CITY_IN_USE);
        }
    }

    public City findIfExists(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

}
