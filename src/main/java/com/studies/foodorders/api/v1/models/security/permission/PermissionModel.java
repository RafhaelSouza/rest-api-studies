package com.studies.foodorders.api.v1.models.security.permission;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "permissions")
@Setter
@Getter
public class PermissionModel extends RepresentationModel<PermissionModel> {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "SEARCH_KITCHEN")
	private String name;

	@ApiModelProperty(example = "Allow to search kitchens")
	private String description;
	
}