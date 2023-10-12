package com.studies.foodorders.api.v2.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.v2.models.kitchen.KitchenInputV2;
import com.studies.foodorders.api.v2.models.kitchen.KitchenModelV2;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Kitchens")
public interface KitchenControllerV2OpenApi {

    @ApiOperation("List of kitchens")
    PagedModel<KitchenModelV2> list(Pageable pageable);

    @ApiOperation("Find a kitchen by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid kitchen id", response = ApiError.class),
            @ApiResponse(code = 404, message = "Kitchen not found", response = ApiError.class)
    })
    KitchenModelV2 find(@ApiParam(value = "Kitchen id", example = "1", required = true) Long id);

    @ApiOperation("Register a kitchen")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Registered kitchen")
    })
    KitchenModelV2 save(@ApiParam(name = "Request body", value = "Representation of a new kitchen", required = true) KitchenInputV2 kitchenInput);

    @ApiOperation("Update a kitchen")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated kitchen"),
            @ApiResponse(code = 404, message = "Kitchen not found", response = ApiError.class)
    })
    KitchenModelV2 update(@ApiParam(value = "Kitchen id", example = "1", required = true) Long id,
                        @ApiParam(name = "Request Body", value = "Representation of a kitchen with the new values", required = true) KitchenInputV2 kitchenInput);

    @ApiOperation("Delete a kitchen")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Deleted kitchen"),
            @ApiResponse(code = 404, message = "Kitchen not found", response = ApiError.class)
    })
    void delete(@ApiParam(value = "Kitchen id", example = "1", required = true) Long id);

}
