package com.studies.foodorders.api.controllers.restaurant;

import com.studies.foodorders.api.assemblers.paymentway.PaymentWayModelAssembler;
import com.studies.foodorders.api.links.RestaurantLinks;
import com.studies.foodorders.api.model.paymentway.PaymentWayModel;
import com.studies.foodorders.api.openapi.controllers.RestaurantPaymentWayControllerOpenApi;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.services.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/payment-ways")
public class RestaurantPaymentWayController implements RestaurantPaymentWayControllerOpenApi {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private PaymentWayModelAssembler paymentWayModelAssembler;

	@Autowired
	private RestaurantLinks restaurantLinks;
	
	@GetMapping
	public CollectionModel<PaymentWayModel> list(@PathVariable Long restaurantId) {
		Restaurant restaurant = restaurantService.findIfExists(restaurantId);
		
		return paymentWayModelAssembler.toCollectionModel(restaurant.getPaymentWay())
				.removeLinks()
				.add(restaurantLinks.linkToRestaurantPaymentWays(restaurantId));
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