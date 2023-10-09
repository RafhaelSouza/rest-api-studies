package com.studies.foodorders.api.links;

import com.studies.foodorders.api.controllers.restaurant.RestaurantController;
import com.studies.foodorders.api.controllers.restaurant.RestaurantPaymentWayController;
import com.studies.foodorders.api.controllers.restaurant.RestaurantResponsibleUserController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RestaurantLinks {

    public Link linkToRestaurant(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .find(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurant(Long restaurantId) {
        return linkToRestaurant(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurants(String rel) {
        String restaurantsUrl = linkTo(RestaurantController.class).toUri().toString();

        return new Link(UriTemplate.of(restaurantsUrl, CommonLinks.VARIABLES_PROJECTION), rel);
    }

    public Link linkToRestaurants() {
        return linkToRestaurants(IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurantPaymentWays(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantPaymentWayController.class)
                .list(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantPaymentWays(Long restaurantId) {
        return linkToRestaurantPaymentWays(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurantPaymentWayAssociation(Long restaurantId, String rel) {

        return linkTo(methodOn(RestaurantPaymentWayController.class)
                .disassociate(restaurantId, null)).withRel(rel);
    }

    public Link linkToRestaurantPaymentWayDisassociation(Long restaurantId, Long PaymentWayId, String rel) {

        return linkTo(methodOn(RestaurantPaymentWayController.class)
                .disassociate(restaurantId, PaymentWayId)).withRel(rel);
    }

    public Link linkToRestaurantResponsible(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantResponsibleUserController.class)
                .list(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantResponsibleAssociation(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantResponsibleUserController.class)
                .associate(restaurantId, null)).withRel(rel);
    }

    public Link linkToRestaurantResponsibleDisassociation(Long restaurantId, Long userId, String rel) {

        return linkTo(methodOn(RestaurantResponsibleUserController.class)
                .disassociate(restaurantId, userId)).withRel(rel);
    }

    public Link linkToRestaurantResponsible(Long restaurantId) {
        return linkToRestaurantResponsible(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurantOpening(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .toOpen(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantClosing(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .toClose(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantActivation(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .activate(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantInactivation(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .inactivate(restaurantId)).withRel(rel);
    }

}
