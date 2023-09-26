package com.studies.foodorders.api.model.security.group;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GroupModel {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Manager")
	private String name;
	
}
