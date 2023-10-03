package com.studies.foodorders.api.links;

import com.studies.foodorders.api.controllers.restaurant.RestaurantController;
import com.studies.foodorders.api.controllers.restaurant.RestaurantPaymentWayController;
import com.studies.foodorders.api.controllers.restaurant.RestaurantResponsibleUserController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
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
        return linkTo(RestaurantController.class).withRel(rel);
    }

    public Link linkToRestaurants() {
        return linkToRestaurants(IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurantPaymentWay(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantPaymentWayController.class)
                .list(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantResponsible(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantResponsibleUserController.class)
                .list(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantResponsible(Long restaurantId) {
        return linkToRestaurantResponsible(restaurantId, IanaLinkRelations.SELF.value());
    }

}
