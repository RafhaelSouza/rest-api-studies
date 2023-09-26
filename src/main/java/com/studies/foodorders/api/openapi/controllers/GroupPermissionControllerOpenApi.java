package com.studies.foodorders.api.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.model.security.permission.PermissionModel;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Groups")
public interface GroupPermissionControllerOpenApi {

    @ApiOperation("List the permissions associated with a group")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid group id", response = ApiError.class),
            @ApiResponse(code = 404, message = "Group not found", response = ApiError.class)
    })
    List<PermissionModel> list(@ApiParam(value = "Group id", example = "1", required = true) Long groupId);

    @ApiOperation("Permission association with group")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Association done successfully"),
            @ApiResponse(code = 404, message = "Group or permission not found", response = ApiError.class)
    })
    void associate(@ApiParam(value = "Group id", example = "1", required = true) Long groupId,
                   @ApiParam(value = "Permission id", example = "1", required = true) Long permissionId);

    @ApiOperation("Permission disassociation with group")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Disassociation done successfully"),
            @ApiResponse(code = 404, message = "Group or permission not found", response = ApiError.class)
    })
    void disassociate(@ApiParam(value = "Group id", example = "1", required = true) Long groupId,
                      @ApiParam(value = "Permission id", example = "1", required = true) Long permissionId);

}
