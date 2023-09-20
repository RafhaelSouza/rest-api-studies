package com.studies.foodorders.api.model.paymentway;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentWayModel {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Credit Card", required = true)
	private String description;
	
}
