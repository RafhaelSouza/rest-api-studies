package com.studies.foodorders.api.links;

import com.studies.foodorders.api.controllers.paymentway.PaymentWayController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PaymentWayLinks {

    public Link linkToPaymentWay(Long paymentWayId, String rel) {
        return linkTo(methodOn(PaymentWayController.class)
                .find(paymentWayId, null)).withRel(rel);
    }

    public Link linkToPaymentWay(Long paymentWayId) {
        return linkToPaymentWay(paymentWayId, IanaLinkRelations.SELF.value());
    }

}
