package com.studies.foodorders.domain.listeners;

import com.studies.foodorders.domain.events.ConfirmedOrderEvent;
import com.studies.foodorders.domain.models.order.Order;
import com.studies.foodorders.domain.services.email.EmailSendingService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ConfirmedOrderCustomerNotificationListener {

    private final EmailSendingService emailSendingService;

    public ConfirmedOrderCustomerNotificationListener(EmailSendingService emailSendingService) {
        this.emailSendingService = emailSendingService;
    }

    @EventListener
    public void whenToConfirmOrder(ConfirmedOrderEvent confirmedOrderEvent) {

        Order order = confirmedOrderEvent.getOrder();

        var message = EmailSendingService.Message.builder()
                .subject(order.getRestaurant().getName() + " - Confirmed Order")
                .body("confirmed-order.html")
                .model("order", order)
                .recipient(order.getClient().getEmail())
                .build();

        emailSendingService.send(message);
    }

}
