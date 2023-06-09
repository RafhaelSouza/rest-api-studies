package com.studies.foodorders.api.controllers.security;

import com.studies.foodorders.api.converter.security.UserModelConverter;
import com.studies.foodorders.api.model.security.user.PasswordInput;
import com.studies.foodorders.api.model.security.user.UserInput;
import com.studies.foodorders.api.model.security.user.UserModel;
import com.studies.foodorders.api.model.security.user.UserWithPasswordInput;
import com.studies.foodorders.domain.models.security.User;
import com.studies.foodorders.domain.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserModelConverter userModelConverter;

    @GetMapping
    public List<UserModel> list() {
        List<User> allUsers = userService.list();

        return userModelConverter.toCollectionModel(allUsers);
    }

    @GetMapping("/{id}")
    public UserModel find(@PathVariable Long id) {
        User user = userService.findIfExists(id);

        return userModelConverter.toModel(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel save(@RequestBody @Valid UserWithPasswordInput userWithPasswordInput) {
        User user = userModelConverter.toDomainObject(userWithPasswordInput);
        user = userService.save(user);

        return userModelConverter.toModel(user);
    }

    @PutMapping("/{id}")
    public UserModel update(@PathVariable Long id,
                                  @RequestBody @Valid UserInput userInput) {
        User currentUser = userService.findIfExists(id);
        userModelConverter.copyToDomainObject(userInput, currentUser);
        currentUser = userService.save(currentUser);

        return userModelConverter.toModel(currentUser);
    }

    @PutMapping("/{id}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassord(@PathVariable Long id, @RequestBody @Valid PasswordInput password) {
        userService.updatePassword(id, password.getCurrentPassword(), password.getNewPassword());
    }

}
