package com.studies.foodorders.domain.services.order;

import com.studies.foodorders.domain.exceptions.OrderNotFoundException;
import com.studies.foodorders.domain.models.order.Order;
import com.studies.foodorders.domain.repositories.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order findIfExists(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

}
