package com.studies.foodorders.api.model.security;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class GroupInput {

	@NotBlank
	private String name;
	
}
