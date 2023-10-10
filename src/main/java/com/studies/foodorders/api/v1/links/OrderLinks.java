package com.studies.foodorders.api.v1.links;

import com.studies.foodorders.api.v1.controllers.order.OrderController;
import com.studies.foodorders.api.v1.controllers.order.OrderFlowController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderLinks {

    public Link linkToOrders(String rel) {
        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("clientId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("restaurantId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("creationDateStart", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("creationDateEnd", TemplateVariable.VariableType.REQUEST_PARAM));

        String ordersUrl = linkTo(OrderController.class).toUri().toString();

        return new Link(UriTemplate.of(ordersUrl, CommonLinks.VARIABLES_PAGINATION.concat(filterVariables)), rel);
    }

    public Link linkToOrderConfirmation(String orderCode, String rel) {
        return linkTo(methodOn(OrderFlowController.class)
                .confirm(orderCode)).withRel(rel);
    }

    public Link linkToOrderDeliver(String orderCode, String rel) {
        return linkTo(methodOn(OrderFlowController.class)
                .deliver(orderCode)).withRel(rel);
    }

    public Link linkToOrderCancellation(String orderCode, String rel) {
        return linkTo(methodOn(OrderFlowController.class)
                .cancel(orderCode)).withRel(rel);
    }

}
