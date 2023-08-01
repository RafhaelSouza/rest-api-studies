package com.studies.foodorders.api.controllers.security;

import com.studies.foodorders.api.converter.security.GroupModelConverter;
import com.studies.foodorders.api.model.security.group.GroupModel;
import com.studies.foodorders.domain.models.security.User;
import com.studies.foodorders.domain.services.security.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users/{userId}/groups")
public class UserGroupController {

    private UserService userService;

    private GroupModelConverter groupModelConverter;

    public UserGroupController(UserService userService, GroupModelConverter groupModelConverter) {
        this.userService = userService;
        this.groupModelConverter = groupModelConverter;
    }

    @GetMapping
    public List<GroupModel> list(@PathVariable Long userId) {
        User user = userService.findIfExists(userId);

        return groupModelConverter.toCollectionModel(user.getGroups());
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
