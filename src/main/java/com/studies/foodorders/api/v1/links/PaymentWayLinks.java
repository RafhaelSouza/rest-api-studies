package com.studies.foodorders.api.v1.links;

import com.studies.foodorders.api.v1.controllers.paymentway.PaymentWayController;
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

    public Link linkToPaymentWays(String rel) {
        return linkTo(PaymentWayController.class).withRel(rel);
    }

    public Link linkToPaymentWays() {
        return linkToPaymentWays(IanaLinkRelations.SELF.value());
    }

}
