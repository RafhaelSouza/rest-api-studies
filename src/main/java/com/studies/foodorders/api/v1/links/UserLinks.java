package com.studies.foodorders.api.v1.links;

import com.studies.foodorders.api.v1.controllers.security.UserController;
import com.studies.foodorders.api.v1.controllers.security.UserGroupController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserLinks {

    public Link linkToUser(Long userId, String rel) {
        return linkTo(methodOn(UserController.class)
                .find(userId)).withRel(rel);
    }

    public Link linkToUser(Long userId) {
        return linkToUser(userId, IanaLinkRelations.SELF.value());
    }

    public Link linkToUsers(String rel) {
        return linkTo(UserController.class).withRel(rel);
    }

    public Link linkToUsers() {
        return linkToUsers(IanaLinkRelations.SELF.value());
    }

    public Link linkToUserGroups(Long userId, String rel) {
        return linkTo(methodOn(UserGroupController.class)
                .list(userId)).withRel(rel);
    }

    public Link linkToUserGroups(Long userId) {
        return linkToUserGroups(userId, IanaLinkRelations.SELF.value());
    }

    public Link linkToUserGroupAssociate(Long userId, String rel) {
        return linkTo(methodOn(UserGroupController.class)
                .associate(userId, null)).withRel(rel);
    }

    public Link linkToUserGroupDisassociate(Long userId, Long groupId, String rel) {
        return linkTo(methodOn(UserGroupController.class)
                .disassociate(userId, groupId)).withRel(rel);
    }

}
