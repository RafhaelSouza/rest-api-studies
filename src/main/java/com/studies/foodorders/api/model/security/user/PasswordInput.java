package com.studies.foodorders.api.model.security.user;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordInput {

	@ApiModelProperty(example = "987", required = true)
	@NotBlank
	private String currentPassword;

	@ApiModelProperty(example = "987", required = true)
	@NotBlank
	private String newPassword;
}