package com.studies.foodorders.api.v2.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.v2.models.localization.city.CityInputV2;
import com.studies.foodorders.api.v2.models.localization.city.CityModelV2;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Cities")
public interface CityControllerV2OpenApi {

    @ApiOperation("List of cities")
    CollectionModel<CityModelV2> list();

    @ApiOperation("Find a city by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid city id", response = ApiError.class),
            @ApiResponse(code = 404, message = "City not found", response = ApiError.class)
    })
    CityModelV2 find(@ApiParam(value = "City id", example = "1", required = true) Long id);

    @ApiOperation("Register a city")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Registered city")
    })
    CityModelV2 save(@ApiParam(name = "Request body", value = "Representation of a new city", required = true) CityInputV2 cityInput);

    @ApiOperation("Update a city")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated city"),
            @ApiResponse(code = 404, message = "City not found", response = ApiError.class)
    })
    CityModelV2 update(
            @ApiParam(value = "City id", example = "1", required = true) Long id,
            @ApiParam(name = "Request Body", value = "Representation of a city with the new values", required = true) CityInputV2 cityInput);

    @ApiOperation("Delete a city")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Deleted city"),
            @ApiResponse(code = 404, message = "City not found", response = ApiError.class)
    })
    void delete(@ApiParam(value = "City id", example = "1", required = true) Long id);

}
