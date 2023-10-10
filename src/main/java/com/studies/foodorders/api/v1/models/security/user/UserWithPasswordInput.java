package com.studies.foodorders.api.v1.models.security.user;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserWithPasswordInput extends UserInput {

	@ApiModelProperty(example = "987", required = true)
	@NotBlank
	private String password;
	
}