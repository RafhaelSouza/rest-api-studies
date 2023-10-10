package com.studies.foodorders.api.v1.links;

import com.studies.foodorders.api.v1.controllers.restaurant.RestaurantProductController;
import com.studies.foodorders.api.v1.controllers.restaurant.RestaurantProductPhotoController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductLinks {

    public Link linkToProduct(Long restaurantId, Long productId, String rel) {
        return linkTo(methodOn(RestaurantProductController.class)
                .find(restaurantId, productId))
                .withRel(rel);
    }

    public Link linkToProduct(Long restaurantId, Long productId) {
        return linkToProduct(restaurantId, productId, IanaLinkRelations.SELF.value());
    }

    public Link linkToProducts(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantProductController.class)
                .list(restaurantId, null)).withRel(rel);
    }

    public Link linkToProducts(Long restaurantId) {
        return linkToProducts(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link linkToProductPhoto(Long restaurantId, Long productId, String rel) {
        return linkTo(methodOn(RestaurantProductPhotoController.class)
                .find(restaurantId, productId)).withRel(rel);
    }

    public Link linkToProductPhoto(Long restaurantId, Long productId) {
        return linkToProductPhoto(restaurantId, productId, IanaLinkRelations.SELF.value());
    }

}
