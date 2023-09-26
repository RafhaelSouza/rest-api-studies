package com.studies.foodorders.api.model.security.permission;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissionModel {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "SEARCH_KITCHEN")
	private String name;

	@ApiModelProperty(example = "Allow to search kitchens")
	private String description;
	
}