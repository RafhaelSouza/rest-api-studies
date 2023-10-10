package com.studies.foodorders.api.v1.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.v1.models.security.permission.PermissionModel;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Groups")
public interface GroupPermissionControllerOpenApi {

    @ApiOperation("List the permissions associated with a group")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid group id", response = ApiError.class),
            @ApiResponse(code = 404, message = "Group not found", response = ApiError.class)
    })
    CollectionModel<PermissionModel> list(@ApiParam(value = "Group id", example = "1", required = true) Long groupId);

    @ApiOperation("Permission association with group")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Association done successfully"),
            @ApiResponse(code = 404, message = "Group or permission not found", response = ApiError.class)
    })
    ResponseEntity<Void> associate(@ApiParam(value = "Group id", example = "1", required = true) Long groupId,
                             @ApiParam(value = "Permission id", example = "1", required = true) Long permissionId);

    @ApiOperation("Permission disassociation with group")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Disassociation done successfully"),
            @ApiResponse(code = 404, message = "Group or permission not found", response = ApiError.class)
    })
    ResponseEntity<Void> disassociate(@ApiParam(value = "Group id", example = "1", required = true) Long groupId,
                      @ApiParam(value = "Permission id", example = "1", required = true) Long permissionId);

}
