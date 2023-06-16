package com.studies.foodorders.api.converter.paymentway;

import com.studies.foodorders.api.model.paymentway.PaymentWayInput;
import com.studies.foodorders.api.model.paymentway.PaymentWayModel;
import com.studies.foodorders.domain.models.paymentway.PaymentWay;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentWayModelConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public PaymentWayModel toModel(PaymentWay paymentWay) {
		return modelMapper.map(paymentWay, PaymentWayModel.class);
	}
	
	public List<PaymentWayModel> toCollectionModel(Collection<PaymentWay> paymentWays) {
		return paymentWays.stream()
				.map(paymentWay -> toModel(paymentWay))
				.collect(Collectors.toList());
	}

	public PaymentWay toDomainObject(PaymentWayInput paymentWayInput) {
		return modelMapper.map(paymentWayInput, PaymentWay.class);
	}

	public void copyToDomainObject(PaymentWayInput paymentWayInput, PaymentWay paymentWay) {
		modelMapper.map(paymentWayInput, paymentWay);
	}
	
}
