package com.studies.foodorders.domain.services.order;

import com.studies.foodorders.domain.models.order.Order;
import com.studies.foodorders.domain.services.email.EmailSendingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderFlowService {

    private OrderService orderService;

    private EmailSendingService emailSendingService;

    public OrderFlowService(OrderService orderService, EmailSendingService emailSendingService) {
        this.orderService = orderService;
        this.emailSendingService = emailSendingService;
    }

    @Transactional
    public void confirm(String orderCode) {
        Order order = orderService.findIfExists(orderCode);
        order.confirm();

        var message = EmailSendingService.Message.builder()
                .subject(order.getRestaurant().getName() + " - Confirmed Order")
                .body("confirmed-order.html")
                .model("order", order)
                .recipient(order.getClient().getEmail())
                .build();

        emailSendingService.send(message);
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
