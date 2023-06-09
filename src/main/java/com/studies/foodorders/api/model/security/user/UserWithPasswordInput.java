package com.studies.foodorders.api.model.security.user;

import javax.validation.constraints.NotBlank;

import com.studies.foodorders.api.model.security.user.UserInput;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserWithPasswordInput extends UserInput {

	@NotBlank
	private String password;
	
}