package com.studies.foodorders.domain.services.order;

import com.studies.foodorders.domain.exceptions.BusinessException;
import com.studies.foodorders.domain.exceptions.OrderNotFoundException;
import com.studies.foodorders.domain.models.localization.City;
import com.studies.foodorders.domain.models.order.Order;
import com.studies.foodorders.domain.models.paymentway.PaymentWay;
import com.studies.foodorders.domain.models.product.Product;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.models.security.Users;
import com.studies.foodorders.domain.repositories.order.OrderRepository;
import com.studies.foodorders.domain.services.localization.CityService;
import com.studies.foodorders.domain.services.paymentway.PaymentWayService;
import com.studies.foodorders.domain.services.product.ProductService;
import com.studies.foodorders.domain.services.restaurant.RestaurantService;
import com.studies.foodorders.domain.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentWayService paymentWayService;

    @Transactional
    public Order makeOrder(Order order) {
        checkOrder(order);
        checkItems(order);

        order.setShippingCosts(order.getRestaurant().getShippingCosts());
        order.calculateTotalPrice();

        return orderRepository.save(order);
    }

    private void checkOrder(Order order) {
        City city = cityService.findIfExists(order.getDeliveryAddress().getCity().getId());
        Users users = userService.findIfExists(order.getClient().getId());
        Restaurant restaurant = restaurantService.findIfExists(order.getRestaurant().getId());
        PaymentWay paymentWay = paymentWayService.findIfExists(order.getPaymentWay().getId());

        order.getDeliveryAddress().setCity(city);
        order.setClient(users);
        order.setRestaurant(restaurant);
        order.setPaymentWay(paymentWay);

        if (restaurant.doesNotAcceptPaymentWay(paymentWay)) {
            throw new BusinessException(String.format("Payment method '%s' is not accepted by this restaurant.",
                    paymentWay.getDescription()));
        }
    }

    private void checkItems(Order order) {
        order.getItems().forEach(item -> {
            Product product = productService.findIfExists(
                    order.getRestaurant().getId(), item.getProduct().getId());

            item.setOrder(order);
            item.setProduct(product);
            item.setUnitPrice(product.getPrice());
        });
    }

    public Order findIfExists(String code) {
        return orderRepository.findByCode(UUID.fromString(code))
                .orElseThrow(() -> new OrderNotFoundException(code));
    }

}
