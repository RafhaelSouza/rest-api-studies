package com.studies.foodorders.domain.services.restaurant;

import com.studies.foodorders.domain.exceptions.RestaurantNotFoundException;
import com.studies.foodorders.domain.exceptions.UsedEntityException;
import com.studies.foodorders.domain.models.kitchen.Kitchen;
import com.studies.foodorders.domain.models.localization.City;
import com.studies.foodorders.domain.models.paymentway.PaymentWay;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.repositories.restaurant.RestaurantRepository;
import com.studies.foodorders.domain.services.kitchen.KitchenService;
import com.studies.foodorders.domain.services.localization.CityService;
import com.studies.foodorders.domain.services.paymentway.PaymentWayService;
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
    private KitchenService kitchenService;

    @Autowired
    private CityService cityService;

    @Autowired
    private PaymentWayService paymentWayService;

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
        Long cityId = restaurant.getAddress().getCity().getId();

        Kitchen kitchen = kitchenService.findIfExists(kitchenId);
        City city = cityService.findIfExists(cityId);

        restaurant.setKitchen(kitchen);
        restaurant.getAddress().setCity(city);

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

    @Transactional
    public void activate(Long id) {
        Restaurant restauranteAtual = findIfExists(id);

        restauranteAtual.activate();
    }

    @Transactional
    public void inactivate(Long id) {
        Restaurant restauranteAtual = findIfExists(id);

        restauranteAtual.inactivate();
    }

    @Transactional
    public void associatePaymentWay(Long restaurantId, Long paymentWayId) {
        Restaurant restaurant = findIfExists(restaurantId);
        PaymentWay paymentWay = paymentWayService.findIfExists(paymentWayId);

        restaurant.addPaymentWay(paymentWay);
    }

    @Transactional
    public void disassociatePaymentWay(Long restaurantId, Long paymentWayId) {
        Restaurant restaurant = findIfExists(restaurantId);
        PaymentWay paymentWay = paymentWayService.findIfExists(paymentWayId);

        restaurant.deletePaymentWay(paymentWay);
    }

    public Restaurant findIfExists(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

}
