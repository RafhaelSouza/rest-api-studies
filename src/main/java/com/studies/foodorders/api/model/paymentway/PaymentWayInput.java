package com.studies.foodorders.api.model.paymentway;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class PaymentWayInput {

	@NotBlank
	private String description;
	
}
