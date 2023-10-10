package com.studies.foodorders.api.v1.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.v1.models.security.user.UserModel;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Restaurants")
public interface RestaurantResponsibleUserControllerOpenApi {

    @ApiOperation("List responsible users associated with the restaurant")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Restaurant not found", response = ApiError.class)
    })
    CollectionModel<UserModel> list(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId);

    @ApiOperation("Restaurant association with responsible user")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Association done successfully"),
            @ApiResponse(code = 404, message = "Restaurant or user not",
                    response = ApiError.class)
    })
    ResponseEntity<Void> associate(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId,
                   @ApiParam(value = "User id", example = "1", required = true) Long userId);

    @ApiOperation("Disassociation of restaurant with responsible user")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Disassociation done successfully"),
            @ApiResponse(code = 404, message = "Restaurant or user not found",
                    response = ApiError.class)
    })
    ResponseEntity<Void> disassociate(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId,
                                      @ApiParam(value = "User id", example = "1", required = true) Long userId);

}
