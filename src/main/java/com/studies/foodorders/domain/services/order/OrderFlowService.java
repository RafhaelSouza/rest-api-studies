package com.studies.foodorders.domain.services.order;

import com.studies.foodorders.domain.models.order.Order;
import com.studies.foodorders.domain.repositories.order.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderFlowService {
    private OrderService orderService;

    private final OrderRepository orderRepository;

    public OrderFlowService(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void confirm(String orderCode) {
        Order order = orderService.findIfExists(orderCode);
        order.confirm();

        // In order to Spring to call events it is necessary call a repository from the aggregate
        orderRepository.save(order);
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
