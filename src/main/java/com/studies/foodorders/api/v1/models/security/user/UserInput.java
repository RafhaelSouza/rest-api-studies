package com.studies.foodorders.api.v1.models.security.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInput {

	@ApiModelProperty(example = "User One", required = true)
	@NotBlank
	private String name;

	@ApiModelProperty(example = "email@domain.com", required = true)
	@NotBlank
	@Email
	private String email;
	
}