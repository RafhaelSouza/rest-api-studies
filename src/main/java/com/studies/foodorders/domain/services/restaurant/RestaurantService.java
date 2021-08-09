package com.studies.foodorders.domain.services.restaurant;

import com.studies.foodorders.domain.exceptions.KitchenNotFoundException;
import com.studies.foodorders.domain.exceptions.RestaurantNotFoundException;
import com.studies.foodorders.domain.exceptions.UsedEntityException;
import com.studies.foodorders.domain.models.kitchen.Kitchen;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.repositories.kitchen.KitchenRepository;
import com.studies.foodorders.domain.repositories.restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    public static final String RESTAURANT_IN_USE = "Restaurant in use.";

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    public List<Restaurant> list() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> find(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant;
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new KitchenNotFoundException(kitchenId));
        restaurant.setKitchen(kitchen);
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public void delete(Long id) {
        try {
            restaurantRepository.deleteById(id);
            restaurantRepository.flush();
        }
        catch (EmptyResultDataAccessException e) {
            throw new RestaurantNotFoundException(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new UsedEntityException(RESTAURANT_IN_USE);
        }
    }

    public Restaurant findIfExists(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

}
