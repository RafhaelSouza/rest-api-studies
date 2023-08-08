package com.studies.foodorders.domain.services.order;

import com.studies.foodorders.domain.exceptions.BusinessException;
import com.studies.foodorders.domain.models.order.Order;
import com.studies.foodorders.domain.models.order.enumerations.OrderStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class OrderFlowService {

    private OrderService orderService;

    public OrderFlowService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Transactional
    public void confirm(Long orderId) {
        Order order = orderService.findIfExists(orderId);

        if (!order.getStatus().equals(OrderStatus.CREATED)) {
            throw new BusinessException(
                    String.format("Order status %d cannot be changed from %s to %s",
                            order.getId(), order.getStatus().getDescription(),
                            OrderStatus.CONFIRMED.getDescription()));
        }

        order.setStatus(OrderStatus.CONFIRMED);
        order.setConfirmedAt(OffsetDateTime.now());
    }

    @Transactional
    public void cancel(Long orderId) {
        Order order = orderService.findIfExists(orderId);

        if (!order.getStatus().equals(OrderStatus.CREATED)) {
            throw new BusinessException(
                    String.format("Order status %d cannot be changed from %s to %s",
                            order.getId(), order.getStatus().getDescription(),
                            OrderStatus.CANCELLED.getDescription()));
        }

        order.setStatus(OrderStatus.CANCELLED);
        order.setConfirmedAt(OffsetDateTime.now());
    }

    @Transactional
    public void deliver(Long orderId) {
        Order order = orderService.findIfExists(orderId);

        if (!order.getStatus().equals(OrderStatus.CONFIRMED)) {
            throw new BusinessException(
                    String.format("Order status %d cannot be changed from %s to %s",
                            order.getId(), order.getStatus().getDescription(),
                            OrderStatus.DELIVERED.getDescription()));
        }

        order.setStatus(OrderStatus.DELIVERED);
        order.setConfirmedAt(OffsetDateTime.now());
    }

}
