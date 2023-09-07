package com.studies.foodorders.domain.listeners;

import com.studies.foodorders.domain.events.CancelledOrderEvent;
import com.studies.foodorders.domain.models.order.Order;
import com.studies.foodorders.domain.services.email.EmailSendingService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class CancelledOrderCustomerNotificationListener {

    private final EmailSendingService emailSendingService;

    public CancelledOrderCustomerNotificationListener(EmailSendingService emailSendingService) {
        this.emailSendingService = emailSendingService;
    }

    // If the phase is not set then the event happens after the transaction has being committed.
    // So if only the event throws an exception the request return success
    @TransactionalEventListener//(phase = TransactionPhase.BEFORE_COMMIT)
    public void whenToCancelOrder(CancelledOrderEvent cancelledOrderEvent) {

        Order order = cancelledOrderEvent.getOrder();

        var message = EmailSendingService.Message.builder()
                .subject(order.getRestaurant().getName() + " - Cancelled Order")
                .body("cancelled-order.html")
                .model("order", order)
                .recipient(order.getClient().getEmail())
                .build();

        emailSendingService.send(message);
    }

}
