package com.studies.foodorders.api.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.model.localization.city.CityInput;
import com.studies.foodorders.api.model.localization.city.CityModel;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Cities")
public interface CityControllerOpenApi {

    @ApiOperation("List of cities")
    CollectionModel<CityModel> list();

    @ApiOperation("Find a city by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid city id", response = ApiError.class),
            @ApiResponse(code = 404, message = "City not found", response = ApiError.class)
    })
    CityModel find(@ApiParam(value = "City id", example = "1", required = true) Long id);

    @ApiOperation("Register a city")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Registered city")
    })
    CityModel save(@ApiParam(name = "Request body", value = "Representation of a new city", required = true) CityInput cityInput);

    @ApiOperation("Update a city")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated city"),
            @ApiResponse(code = 404, message = "City not found", response = ApiError.class)
    })
    CityModel update(
            @ApiParam(value = "City id", example = "1", required = true) Long id,
            @ApiParam(name = "Request Body", value = "Representation of a city with the new values", required = true) CityInput cityInput);

    @ApiOperation("Delete a city")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Deleted city"),
            @ApiResponse(code = 404, message = "City not found", response = ApiError.class)
    })
    void delete(@ApiParam(value = "City id", example = "1", required = true) Long id);

}
