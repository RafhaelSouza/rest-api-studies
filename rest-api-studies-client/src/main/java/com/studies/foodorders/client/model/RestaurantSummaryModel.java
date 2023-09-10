package com.studies.foodorders.client.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestaurantSummaryModel {

	private Long id;
	private String name;
	private BigDecimal shippingCosts;
	private KitchenModel kitchen;
	
}
