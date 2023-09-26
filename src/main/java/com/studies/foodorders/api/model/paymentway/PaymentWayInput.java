package com.studies.foodorders.api.model.paymentway;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class PaymentWayInput {

	@ApiModelProperty(example = "Credit Card", required = true)
	@NotBlank
	private String description;
	
}
