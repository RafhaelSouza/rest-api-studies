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
    public void confirm(String orderCode) {
        Order order = orderService.findIfExists(orderCode);
        order.confirm();
    }

    @Transactional
    public void cancel(String orderCode) {
        Order order = orderService.findIfExists(orderCode);
        order.cancel();
    }

    @Transactional
    public void deliver(String orderCode) {
        Order order = orderService.findIfExists(orderCode);
        order.deliver();
    }

}
