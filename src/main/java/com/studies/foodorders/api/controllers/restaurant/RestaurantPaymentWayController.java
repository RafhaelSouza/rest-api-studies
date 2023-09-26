package com.studies.foodorders.api.controllers.restaurant;

import com.studies.foodorders.api.converter.paymentway.PaymentWayModelConverter;
import com.studies.foodorders.api.model.paymentway.PaymentWayModel;
import com.studies.foodorders.api.openapi.controllers.RestaurantPaymentWayControllerOpenApi;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.services.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/payment-ways")
public class RestaurantPaymentWayController implements RestaurantPaymentWayControllerOpenApi {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private PaymentWayModelConverter paymentWayModelConverter;
	
	@GetMapping
	public List<PaymentWayModel> list(@PathVariable Long restaurantId) {
		Restaurant restaurant = restaurantService.findIfExists(restaurantId);
		
		return paymentWayModelConverter.toCollectionModel(restaurant.getPaymentWay());
	}
	
	@PutMapping("/{paymentWayId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associate(@PathVariable Long restaurantId, @PathVariable Long paymentWayId) {
		restaurantService.associatePaymentWay(restaurantId, paymentWayId);
	}

	@DeleteMapping("/{paymentWayId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disassociate(@PathVariable Long restaurantId, @PathVariable Long paymentWayId) {
		restaurantService.disassociatePaymentWay(restaurantId, paymentWayId);
	}

}