package com.studies.foodorders.api.converter.paymentway;

import com.studies.foodorders.api.model.paymentway.PaymentWayInput;
import com.studies.foodorders.domain.models.paymentway.PaymentWay;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentWayModelDisconverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public PaymentWay toDomainObject(PaymentWayInput paymentWayInput) {
		return modelMapper.map(paymentWayInput, PaymentWay.class);
	}
	
	public void copyToDomainObject(PaymentWayInput paymentWayInput, PaymentWay paymentWay) {
		modelMapper.map(paymentWayInput, paymentWay);
	}
	
}
