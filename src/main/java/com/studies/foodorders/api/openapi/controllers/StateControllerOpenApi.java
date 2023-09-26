package com.studies.foodorders.api.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.model.localization.state.StateInput;
import com.studies.foodorders.api.model.localization.state.StateModel;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "States")
public interface StateControllerOpenApi {

    @ApiOperation("List of states")
    List<StateModel> list();

    @ApiOperation("Find a state by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid state id", response = ApiError.class),
            @ApiResponse(code = 404, message = "State not found", response = ApiError.class)
    })
    StateModel find(@ApiParam(value = "State id", example = "1", required = true) Long id);

    @ApiOperation("Register a state")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Registered state")
    })
    StateModel save(@ApiParam(name = "Request body", value = "Representation of a new state", required = true) StateInput stateInput);

    @ApiOperation("Update a state")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated state"),
            @ApiResponse(code = 404, message = "State not found", response = ApiError.class)
    })
    StateModel update(
            @ApiParam(value = "State id", example = "1", required = true) Long id,
            @ApiParam(name = "Request Body", value = "Representation of a state with the new values", required = true) StateInput stateInput);

    @ApiOperation("Delete a state")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Deleted state"),
            @ApiResponse(code = 404, message = "State not found", response = ApiError.class)
    })
    void delete(@ApiParam(value = "State id", example = "1", required = true) Long id);

}
