package com.studies.foodorders.api.openapi.models;

import com.studies.foodorders.api.model.security.permission.PermissionModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("PermissionsModel")
@Data
public class PermissionsModelOpenApi {

	private PermissionsEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("PermissionsEmbeddedModel")
	@Data
	public class PermissionsEmbeddedModelOpenApi {

		private List<PermissionModel> permissions;

	}

}
