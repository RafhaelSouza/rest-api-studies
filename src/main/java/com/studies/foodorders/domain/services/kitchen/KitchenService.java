package com.studies.foodorders.domain.services.kitchen;

import com.studies.foodorders.domain.exceptions.KitchenNotFoundException;
import com.studies.foodorders.domain.exceptions.UsedEntityException;
import com.studies.foodorders.domain.models.kitchen.Kitchen;
import com.studies.foodorders.domain.repositories.kitchen.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class KitchenService {

    public static final String KITCHEN_IN_USE = "Kitchen in use.";

    @Autowired
    private KitchenRepository kitchenRepository;

    public Page<Kitchen> list(Pageable pageable) {
        return kitchenRepository.findAll(pageable);
    }

    public Optional<Kitchen> find(Long id) {
        Optional<Kitchen> kitchen = kitchenRepository.findById(id);
        return kitchen;
    }

    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    @Transactional
    public Kitchen update(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    @Transactional
    public void delete(Long id) {
        try {
            kitchenRepository.deleteById(id);
            kitchenRepository.flush();
        }
        catch (EmptyResultDataAccessException e) {
            throw new KitchenNotFoundException(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new UsedEntityException(KITCHEN_IN_USE);
        }
    }

    public Kitchen findIfExists(Long id) {
        return kitchenRepository.findById(id)
                .orElseThrow(() -> new KitchenNotFoundException(id));
    }

}
