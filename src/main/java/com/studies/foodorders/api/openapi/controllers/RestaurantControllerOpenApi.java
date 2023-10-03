package com.studies.foodorders.api.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.model.restaurant.RestaurantBasicModel;
import com.studies.foodorders.api.model.restaurant.RestaurantIdAndNameModel;
import com.studies.foodorders.api.model.restaurant.RestaurantModel;
import com.studies.foodorders.api.model.restaurant.input.RestaurantInput;
import com.studies.foodorders.api.openapi.models.RestaurantBasicModelOpenApi;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Restaurants")
public interface RestaurantControllerOpenApi {

    @ApiOperation(value = "List of restaurants", response = RestaurantBasicModelOpenApi.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Order projection name", allowableValues = "id-and-name",
                    name = "projection", paramType = "query", type = "string")
    })
    //@JsonView({ RestaurantView.Summary.class })
    CollectionModel<RestaurantBasicModel> list();

    @ApiOperation(value = "List of restaurants", hidden = true)
    CollectionModel<RestaurantIdAndNameModel> listIdAndName();

    @ApiOperation("Find a restaurant by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid restaurant id", response = ApiError.class),
            @ApiResponse(code = 404, message = "Restaurant not found", response = ApiError.class)
    })
    RestaurantModel find(@ApiParam(value = "Restaurant id", example = "1", required = true) Long id);

    @ApiOperation("Register a restaurant")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Registered restaurant")
    })
    RestaurantModel save(@ApiParam(name = "Request body",
            value = "Representation of a new restaurant",
            required = true) RestaurantInput restaurantInput);

    @ApiOperation("Update a restaurant")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated restaurant"),
            @ApiResponse(code = 404, message = "Restaurant not found", response = ApiError.class)
    })
    RestaurantModel update(@ApiParam(value = "Restaurant id", example = "1", required = true) Long id,
                           @ApiParam(name = "Request Body",
                                        value = "Representation of a restaurant with the new values",
                                        required = true) RestaurantInput restaurantInput);

    @ApiOperation("Activate a restaurant by id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurant activated successfully"),
            @ApiResponse(code = 404, message = "Restaurant not found", response = ApiError.class)
    })
    ResponseEntity<Void> activate(@ApiParam(value = "Restaurant id", example = "1", required = true) Long id);

    @ApiOperation("Inactivate a restaurant by id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurant successfully inactivated"),
            @ApiResponse(code = 404, message = "Restaurant not found", response = ApiError.class)
    })
    ResponseEntity<Void> inactivate(@ApiParam(value = "Restaurant id", example = "1", required = true) Long id);

    @ApiOperation("Activate multiple restaurants")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurants successfully activated")
    })
    void batchActivate(@ApiParam(value = "Restaurant ids", example = "1", required = true) List<Long> restaurantsId);

    @ApiOperation("Inactivate multiple restaurants")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurants successfully activated")
    })
    void batchInactivate(@ApiParam(value = "Restaurant ids", example = "1", required = true) List<Long> restaurantsId);

    @ApiOperation("Open a restaurant by id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurant successfully opened"),
            @ApiResponse(code = 404, message = "Restaurant not found", response = ApiError.class)
    })
    ResponseEntity<Void> toOpen(@ApiParam(value = "Restaurant id", example = "1", required = true) Long id);

    @ApiOperation("Close a restaurant by id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurant successfully closed"),
            @ApiResponse(code = 404, message = "Restaurant not found", response = ApiError.class)
    })
    ResponseEntity<Void> toClose(@ApiParam(value = "Restaurant id", example = "1", required = true) Long id);

}
