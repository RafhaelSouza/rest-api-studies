package com.studies.foodorders.api.model.security.user;

import javax.validation.constraints.NotBlank;

import com.studies.foodorders.api.model.security.user.UserInput;
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