package com.studies.foodorders.api.v1.models.paymentway;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "paymentWays")
@Setter
@Getter
public class PaymentWayModel extends RepresentationModel<PaymentWayModel> {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Credit Card", required = true)
	private String description;
	
}
