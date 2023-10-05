package com.studies.foodorders.api.controllers.security;

import com.studies.foodorders.api.assemblers.security.GroupModelAssembler;
import com.studies.foodorders.api.model.security.group.GroupModel;
import com.studies.foodorders.api.openapi.controllers.UserGroupControllerOpenApi;
import com.studies.foodorders.domain.models.security.User;
import com.studies.foodorders.domain.services.security.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users/{userId}/groups")
public class UserGroupController implements UserGroupControllerOpenApi {

    private UserService userService;

    private GroupModelAssembler groupModelAssembler;

    public UserGroupController(UserService userService, GroupModelAssembler groupModelAssembler) {
        this.userService = userService;
        this.groupModelAssembler = groupModelAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<GroupModel> list(@PathVariable Long userId) {
        User user = userService.findIfExists(userId);

        return groupModelAssembler.toCollectionModel(user.getGroups())
                .removeLinks();
    }

    @PutMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.groupAssociate(userId, groupId);
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.groupDisassociate(userId, groupId);
    }

}
