package com.studies.foodorders.api.controllers.security;

import com.studies.foodorders.api.converter.security.UserModelConverter;
import com.studies.foodorders.api.model.security.user.PasswordInput;
import com.studies.foodorders.api.model.security.user.UserInput;
import com.studies.foodorders.api.model.security.user.UserModel;
import com.studies.foodorders.api.model.security.user.UserWithPasswordInput;
import com.studies.foodorders.api.openapi.controllers.UserControllerOpenApi;
import com.studies.foodorders.domain.models.security.User;
import com.studies.foodorders.domain.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController implements UserControllerOpenApi {

    @Autowired
    private UserService userService;

    @Autowired
    private UserModelConverter userModelConverter;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserModel> list() {
        List<User> allUsers = userService.list();

        return userModelConverter.toCollectionModel(allUsers);
    }

    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel find(@PathVariable Long userId) {
        User user = userService.findIfExists(userId);

        return userModelConverter.toModel(user);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel save(@RequestBody @Valid UserWithPasswordInput userWithPasswordInput) {
        User user = userModelConverter.toDomainObject(userWithPasswordInput);
        user = userService.save(user);

        return userModelConverter.toModel(user);
    }

    @PutMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel update(@PathVariable Long userId,
                                  @RequestBody @Valid UserInput userInput) {
        User currentUser = userService.findIfExists(userId);
        userModelConverter.copyToDomainObject(userInput, currentUser);
        currentUser = userService.save(currentUser);

        return userModelConverter.toModel(currentUser);
    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassord(@PathVariable Long userId, @RequestBody @Valid PasswordInput password) {
        userService.updatePassword(userId, password.getCurrentPassword(), password.getNewPassword());
    }

}
