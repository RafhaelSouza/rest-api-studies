package com.studies.foodorders.api.links;

import com.studies.foodorders.api.controllers.security.GroupController;
import com.studies.foodorders.api.controllers.security.GroupPermissionController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GroupLinks {

    public Link linkToGroups(String rel) {
        return linkTo(GroupController.class).withRel(rel);
    }

    public Link linkToGroups() {
        return linkToGroups(IanaLinkRelations.SELF.value());
    }

    public Link linkToGroupPermissions(Long groupId, String rel) {
        return linkTo(methodOn(GroupPermissionController.class)
                .list(groupId)).withRel(rel);
    }

}
