package com.studies.foodorders.client.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestaurantInput {

	private String name;

	@JsonProperty("shipping_costs")
	private BigDecimal shippingCosts;
	
	private KitchenIdInput kitchen;
	private AddressInput address;
	
}