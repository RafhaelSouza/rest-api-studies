package com.studies.foodorders.domain.services.localization;

import com.studies.foodorders.domain.exceptions.StateNotFoundException;
import com.studies.foodorders.domain.exceptions.UsedEntityException;
import com.studies.foodorders.domain.models.localization.State;
import com.studies.foodorders.domain.repositories.localization.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    public static final String STATE_IN_USE = "State in use.";

    @Autowired
    private StateRepository stateRepository;

    public List<State> list() {
        return stateRepository.findAll();
    }

    public Optional<State> find(Long id) {
        Optional<State> state = stateRepository.findById(id);
        return state;
    }

    @Transactional
    public State save(State state) {
        return stateRepository.save(state);
    }

    @Transactional
    public void delete(Long id) {
        try {
            stateRepository.deleteById(id);
            stateRepository.flush();
        }
        catch (EmptyResultDataAccessException e) {
            throw new StateNotFoundException(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new UsedEntityException(STATE_IN_USE);
        }
    }

    public State findIfExists(Long id) {
        return stateRepository.findById(id)
                .orElseThrow(() -> new StateNotFoundException(id));
    }

}
