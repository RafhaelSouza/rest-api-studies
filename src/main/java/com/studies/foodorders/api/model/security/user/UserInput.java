package com.studies.foodorders.api.model.security.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInput {

	@NotBlank
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
}