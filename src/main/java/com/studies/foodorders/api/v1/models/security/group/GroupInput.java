package com.studies.foodorders.api.v1.models.security.group;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class GroupInput {

	@ApiModelProperty(example = "Manager", required = true)
	@NotBlank
	private String name;
	
}
