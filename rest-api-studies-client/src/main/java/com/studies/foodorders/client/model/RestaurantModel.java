package com.studies.foodorders.client.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestaurantModel {

	private Long id;
	private String name;
	private BigDecimal shippingCosts;
	private Boolean active;
	private Boolean opened;

	private KitchenModel kitchen;
	private AddressModel address;

}