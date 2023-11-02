package com.studies.foodorders.api.v1.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.v1.models.security.user.PasswordInput;
import com.studies.foodorders.api.v1.models.security.user.UserInput;
import com.studies.foodorders.api.v1.models.security.user.UserModel;
import com.studies.foodorders.api.v1.models.security.user.UserWithPasswordInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Users")
public interface UsersControllerOpenApi {

    @ApiOperation("List of users")
    CollectionModel<UserModel> list();

    @ApiOperation("Find a user by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid user id", response = ApiError.class),
            @ApiResponse(code = 404, message = "User not found", response = ApiError.class)
    })
    UserModel find(@ApiParam(value = "User id", example = "1", required = true) Long userId);

    @ApiOperation("Register a user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Registered user")
    })
    UserModel save(@ApiParam(name = "Request body", value = "Representation of a new user", required = true) UserWithPasswordInput userWithPasswordInput);

    @ApiOperation("Update a user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated user"),
            @ApiResponse(code = 404, message = "User not found", response = ApiError.class)
    })
    UserModel update(@ApiParam(value = "User id", example = "1", required = true) Long userId,
                     @ApiParam(name = "Request Body", value = "Representation of a user with the new values", required = true) UserInput userInput);

    @ApiOperation("Update a user's password")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Password changed successfully"),
            @ApiResponse(code = 404, message = "User not found", response = ApiError.class)
    })
    void updatePassord(@ApiParam(value = "User id", example = "1", required = true) Long userId,
                       @ApiParam(name = "Request body", value = "Representation of a new password", required = true) PasswordInput password);

}
