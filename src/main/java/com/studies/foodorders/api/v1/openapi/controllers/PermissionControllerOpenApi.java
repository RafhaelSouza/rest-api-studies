package com.studies.foodorders.api.v1.openapi.controllers;

import com.studies.foodorders.api.v1.models.security.permission.PermissionModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Permissions")
public interface PermissionControllerOpenApi {

	@ApiOperation("List of permissions")
	CollectionModel<PermissionModel> list();

}
