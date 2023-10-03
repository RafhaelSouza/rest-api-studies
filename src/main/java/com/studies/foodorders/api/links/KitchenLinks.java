package com.studies.foodorders.api.links;

import com.studies.foodorders.api.controllers.kitchen.KitchenController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class KitchenLinks {

    public Link linkToKitchens(String rel) {
        return linkTo(KitchenController.class).withRel(rel);
    }

    public Link linkToKitchens() {
        return linkToKitchens(IanaLinkRelations.SELF.value());
    }

}
