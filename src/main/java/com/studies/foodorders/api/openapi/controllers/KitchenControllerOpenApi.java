package com.studies.foodorders.api.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.model.kitchen.KitchenInput;
import com.studies.foodorders.api.model.kitchen.KitchenModel;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Kitchens")
public interface KitchenControllerOpenApi {

    @ApiOperation("List of kitchens")
    PagedModel<KitchenModel> list(Pageable pageable);

    @ApiOperation("Find a kitchen by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid kitchen id", response = ApiError.class),
            @ApiResponse(code = 404, message = "Kitchen not found", response = ApiError.class)
    })
    KitchenModel find(@ApiParam(value = "Kitchen id", example = "1", required = true) Long id);

    @ApiOperation("Register a kitchen")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Registered kitchen")
    })
    KitchenModel save(@ApiParam(name = "Request body", value = "Representation of a new kitchen", required = true) KitchenInput kitchenInput);

    @ApiOperation("Update a kitchen")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated kitchen"),
            @ApiResponse(code = 404, message = "Kitchen not found", response = ApiError.class)
    })
    KitchenModel update(@ApiParam(value = "Kitchen id", example = "1", required = true) Long id,
                        @ApiParam(name = "Request Body", value = "Representation of a kitchen with the new values", required = true) KitchenInput kitchenInput);

    @ApiOperation("Delete a kitchen")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Deleted kitchen"),
            @ApiResponse(code = 404, message = "Kitchen not found", response = ApiError.class)
    })
    void delete(@ApiParam(value = "Kitchen id", example = "1", required = true) Long id);

}
