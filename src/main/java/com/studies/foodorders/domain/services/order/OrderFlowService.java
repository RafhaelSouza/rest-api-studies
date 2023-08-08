package com.studies.foodorders.domain.services.order;

import com.studies.foodorders.domain.models.order.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderFlowService {

    private OrderService orderService;

    public OrderFlowService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Transactional
    public void confirm(Long orderId) {
        Order order = orderService.findIfExists(orderId);
        order.confirm();
    }

    @Transactional
    public void cancel(Long orderId) {
        Order order = orderService.findIfExists(orderId);
        order.cancel();
    }

    @Transactional
    public void deliver(Long orderId) {
        Order order = orderService.findIfExists(orderId);
        order.deliver();
    }

}
