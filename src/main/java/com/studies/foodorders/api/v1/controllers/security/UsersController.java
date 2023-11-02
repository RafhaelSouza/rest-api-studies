package com.studies.foodorders.api.v1.controllers.security;

import com.studies.foodorders.api.v1.assemblers.security.UserModelAssembler;
import com.studies.foodorders.api.v1.models.security.user.PasswordInput;
import com.studies.foodorders.api.v1.models.security.user.UserInput;
import com.studies.foodorders.api.v1.models.security.user.UserModel;
import com.studies.foodorders.api.v1.models.security.user.UserWithPasswordInput;
import com.studies.foodorders.api.v1.openapi.controllers.UsersControllerOpenApi;
import com.studies.foodorders.core.security.CheckSecurity;
import com.studies.foodorders.domain.models.security.Users;
import com.studies.foodorders.domain.services.security.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/users")
public class UsersController implements UsersControllerOpenApi {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @CheckSecurity.UsersGroupsPermissions.AllowToSearch
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<UserModel> list() {
        List<Users> allUsers = usersService.list();

        return userModelAssembler.toCollectionModel(allUsers);
    }

    @CheckSecurity.UsersGroupsPermissions.AllowToSearch
    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel find(@PathVariable Long userId) {
        Users users = usersService.findIfExists(userId);

        return userModelAssembler.toModel(users);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel save(@RequestBody @Valid UserWithPasswordInput userWithPasswordInput) {
        Users users = userModelAssembler.toDomainObject(userWithPasswordInput);
        users = usersService.save(users);

        return userModelAssembler.toModel(users);
    }

    @CheckSecurity.UsersGroupsPermissions.AllowToUpdateUser
    @PutMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel update(@PathVariable Long userId,
                                  @RequestBody @Valid UserInput userInput) {
        Users currentUsers = usersService.findIfExists(userId);
        userModelAssembler.copyToDomainObject(userInput, currentUsers);
        currentUsers = usersService.save(currentUsers);

        return userModelAssembler.toModel(currentUsers);
    }

    @CheckSecurity.UsersGroupsPermissions.AllowToUpdateOwnPassword
    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassord(@PathVariable Long userId, @RequestBody @Valid PasswordInput password) {
        usersService.updatePassword(userId, password.getCurrentPassword(), password.getNewPassword());
    }

}
