package com.studies.foodorders.api.openapi.models;

import com.studies.foodorders.api.model.paymentway.PaymentWayModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("PaymentWaysModel")
@Data
public class PaymentWaysModelOpenApi {

	private PaymentWaysEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("PaymentWaysEmbeddedModel")
	@Data
	public class PaymentWaysEmbeddedModelOpenApi {

		private List<PaymentWayModel> paymentWays;

	}

}
