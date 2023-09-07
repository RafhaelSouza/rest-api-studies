package com.studies.foodorders.domain.listeners;

import com.studies.foodorders.domain.events.ConfirmedOrderEvent;
import com.studies.foodorders.domain.models.order.Order;
import com.studies.foodorders.domain.services.email.EmailSendingService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ConfirmedOrderCustomerNotificationListener {

    private final EmailSendingService emailSendingService;

    public ConfirmedOrderCustomerNotificationListener(EmailSendingService emailSendingService) {
        this.emailSendingService = emailSendingService;
    }

    // If the phase is not set then the event happens after the transaction has being committed.
    // So if only the event throws an exception the request return success
    @TransactionalEventListener//(phase = TransactionPhase.BEFORE_COMMIT)
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
