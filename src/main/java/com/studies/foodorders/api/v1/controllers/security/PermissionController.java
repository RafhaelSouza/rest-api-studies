package com.studies.foodorders.api.v1.controllers.security;

import com.studies.foodorders.api.v1.assemblers.security.PermissionModelAssembler;
import com.studies.foodorders.api.v1.models.security.permission.PermissionModel;
import com.studies.foodorders.api.v1.openapi.controllers.PermissionControllerOpenApi;
import com.studies.foodorders.core.security.CheckSecurity;
import com.studies.foodorders.domain.models.security.Permission;
import com.studies.foodorders.domain.services.security.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissionController implements PermissionControllerOpenApi {

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private PermissionModelAssembler permissionModelAssembler;

	@CheckSecurity.UsersGroupsPermissions.AllowToSearch
	@GetMapping
	public CollectionModel<PermissionModel> list() {
		List<Permission> allPermissions = permissionService.list();

		return permissionModelAssembler.toCollectionModel(allPermissions);
	}

}
