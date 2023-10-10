package com.studies.foodorders.api.v1.controllers.restaurant;

import com.studies.foodorders.api.v1.assemblers.paymentway.PaymentWayModelAssembler;
import com.studies.foodorders.api.v1.links.RestaurantLinks;
import com.studies.foodorders.api.v1.models.paymentway.PaymentWayModel;
import com.studies.foodorders.api.v1.openapi.controllers.RestaurantPaymentWayControllerOpenApi;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.services.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

		CollectionModel<PaymentWayModel> paymentWaysModel =
				paymentWayModelAssembler.toCollectionModel(restaurant.getPaymentWay())
				.removeLinks()
				.add(restaurantLinks.linkToRestaurantPaymentWays(restaurantId))
				.add(restaurantLinks.linkToRestaurantPaymentWayAssociation(restaurantId, "associate"));

		paymentWaysModel.getContent().forEach(paymentWayModel -> {
			paymentWayModel.add(restaurantLinks.linkToRestaurantPaymentWayDisassociation(
					restaurantId, paymentWayModel.getId(), "disassociate"));
		});

		return paymentWaysModel;
	}
	
	@PutMapping("/{paymentWayId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associate(@PathVariable Long restaurantId, @PathVariable Long paymentWayId) {
		restaurantService.associatePaymentWay(restaurantId, paymentWayId);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{paymentWayId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> disassociate(@PathVariable Long restaurantId, @PathVariable Long paymentWayId) {
		restaurantService.disassociatePaymentWay(restaurantId, paymentWayId);

		return ResponseEntity.noContent().build();
	}

}