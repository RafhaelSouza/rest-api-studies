package com.studies.foodorders.client.model;

import lombok.Data;

@Data
public class AddressModel {

	private String postalCode;
	private String street;
	private String number;
	private String complement;
	private String district;
	private CitySummaryModel city;

}