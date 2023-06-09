package com.studies.foodorders.api.model.security.user;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordInput {
	
	@NotBlank
	private String currentPassword;
	
	@NotBlank
	private String newPassword;
}