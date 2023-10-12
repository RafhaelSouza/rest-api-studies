package com.studies.foodorders.api.v1.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.v1.models.security.group.GroupInput;
import com.studies.foodorders.api.v1.models.security.group.GroupModel;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Groups")
public interface GroupControllerOpenApi {

    @ApiOperation("List of groups")
    CollectionModel<GroupModel> list();

    @ApiOperation("Find a group by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid group id", response = ApiError.class),
            @ApiResponse(code = 404, message = "Group not found", response = ApiError.class)
    })
    GroupModel find(@ApiParam(value = "Group id", example = "1", required = true) Long id);

    @ApiOperation("Register a group")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Registered group")
    })
    GroupModel save(@ApiParam(name = "Request body", value = "Representation of a new group", required = true) GroupInput groupInput);

    @ApiOperation("Update a group")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated group"),
            @ApiResponse(code = 404, message = "Group not found", response = ApiError.class)
    })
    GroupModel update(@ApiParam(value = "Group id", example = "1", required = true) Long id,
                      @ApiParam(name = "Request Body", value = "Representation of a group with the new values", required = true) GroupInput groupInput);

    @ApiOperation("Delete a group")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Deleted group"),
            @ApiResponse(code = 404, message = "Group not found", response = ApiError.class)
    })
    void delete(@ApiParam(value = "Group id", example = "1", required = true) Long id);

}
