package com.studies.foodorders.client.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressInput {

	@JsonProperty("postal_code")
	private String postalCode;
	private String street;
	private String number;
	private String complement;
	private String district;
	private CityIdInput city;

}