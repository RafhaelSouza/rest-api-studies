package com.studies.foodorders.api.controllers.security;

import com.studies.foodorders.api.assemblers.security.PermissionModelAssembler;
import com.studies.foodorders.api.model.security.permission.PermissionModel;
import com.studies.foodorders.api.openapi.controllers.PermissionControllerOpenApi;
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
@RequestMapping(path = "/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissionController implements PermissionControllerOpenApi {

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private PermissionModelAssembler permissionModelAssembler;

	@GetMapping
	public CollectionModel<PermissionModel> list() {
		List<Permission> allPermissions = permissionService.list();

		return permissionModelAssembler.toCollectionModel(allPermissions);
	}

}
