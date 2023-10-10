package com.studies.foodorders.api.v1.assemblers.paymentway;

import com.studies.foodorders.api.v1.controllers.paymentway.PaymentWayController;
import com.studies.foodorders.api.v1.links.PaymentWayLinks;
import com.studies.foodorders.api.v1.models.paymentway.PaymentWayInput;
import com.studies.foodorders.api.v1.models.paymentway.PaymentWayModel;
import com.studies.foodorders.domain.models.paymentway.PaymentWay;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PaymentWayModelAssembler extends RepresentationModelAssemblerSupport<PaymentWay, PaymentWayModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PaymentWayLinks paymentWayLinks;

	public PaymentWayModelAssembler() {
		super(PaymentWayController.class, PaymentWayModel.class);
	}

	public PaymentWayModel toModel(PaymentWay paymentWay) {

		PaymentWayModel paymentWayModel =
				createModelWithId(paymentWay.getId(), paymentWay);

		modelMapper.map(paymentWay, paymentWayModel);

		paymentWayModel.add(paymentWayLinks.linkToPaymentWays("paymentWays"));

		return paymentWayModel;
	}

	@Override
	public CollectionModel<PaymentWayModel> toCollectionModel(Iterable<? extends PaymentWay> entities) {
		return super.toCollectionModel(entities)
				.add(paymentWayLinks.linkToPaymentWays());
	}

	public PaymentWay toDomainObject(PaymentWayInput paymentWayInput) {
		return modelMapper.map(paymentWayInput, PaymentWay.class);
	}

	public void copyToDomainObject(PaymentWayInput paymentWayInput, PaymentWay paymentWay) {
		modelMapper.map(paymentWayInput, paymentWay);
	}
	
}
