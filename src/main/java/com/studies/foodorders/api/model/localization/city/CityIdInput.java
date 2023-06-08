package com.studies.foodorders.api.model.localization.city;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CityIdInput {

	@NotNull
	private Long id;
	
}