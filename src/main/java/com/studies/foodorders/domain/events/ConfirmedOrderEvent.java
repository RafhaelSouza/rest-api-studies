package com.studies.foodorders.domain.events;

import com.studies.foodorders.domain.models.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConfirmedOrderEvent {

	private Order order;
	
}
