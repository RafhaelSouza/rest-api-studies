package com.studies.foodorders.api.v1.controllers;

import com.studies.foodorders.api.v1.links.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

	@Autowired
	private CityLinks cityLinks;

	@Autowired
	private StateLinks stateLinks;

	@Autowired
	private KitchenLinks kitchenLinks;

	@Autowired
	private RestaurantLinks restaurantLinks;

	@Autowired
	private PaymentWayLinks paymentWayLinks;

	@Autowired
	private OrderLinks orderLinks;

	@Autowired
	private UserLinks userLinks;

	@Autowired
	private PermissionLinks permissionLinks;

	@Autowired
	private StatisticLinks statisticLinks;

	@GetMapping
	public RootEntryPointModel root() {
		var rootEntryPointModel = new RootEntryPointModel();

		rootEntryPointModel.add(cityLinks.linkToCities("cities"));
		rootEntryPointModel.add(stateLinks.linkToStates("states"));
		rootEntryPointModel.add(kitchenLinks.linkToKitchens("kitchens"));
		rootEntryPointModel.add(restaurantLinks.linkToRestaurants("restaurants"));
		rootEntryPointModel.add(paymentWayLinks.linkToPaymentWays("paymentWays"));
		rootEntryPointModel.add(orderLinks.linkToOrders("orders"));
		rootEntryPointModel.add(userLinks.linkToUsers("users"));
		rootEntryPointModel.add(permissionLinks.linkToPermissions("permissions"));
		rootEntryPointModel.add(statisticLinks.linkToStatistics("statistics"));

		return rootEntryPointModel;
	}

	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {}

}
