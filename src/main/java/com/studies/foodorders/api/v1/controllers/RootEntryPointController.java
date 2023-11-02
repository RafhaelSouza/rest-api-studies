package com.studies.foodorders.api.v1.controllers;

import com.studies.foodorders.api.v1.links.*;
import com.studies.foodorders.core.security.ApiSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
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

	@Autowired
	private GroupLinks groupLinks;

	@Autowired
	private ApiSecurity apiSecurity;

	@GetMapping
	public RootEntryPointModel root() {
		var rootEntryPointModel = new RootEntryPointModel();

		if (apiSecurity.isAllowedToSearchCities())
			rootEntryPointModel.add(cityLinks.linkToCities("cities"));

		if (apiSecurity.isAllowedToSearchStates())
			rootEntryPointModel.add(stateLinks.linkToStates("states"));

		if (apiSecurity.isAllowedToSearchKitchens())
			rootEntryPointModel.add(kitchenLinks.linkToKitchens("kitchens"));

		if (apiSecurity.isAllowedToSearchRestaurants())
			rootEntryPointModel.add(restaurantLinks.linkToRestaurants("restaurants"));

		if (apiSecurity.isAllowedToSearchPaymentWays())
			rootEntryPointModel.add(paymentWayLinks.linkToPaymentWays("paymentWays"));

		if (apiSecurity.isAllowedToSearchOrders())
			rootEntryPointModel.add(orderLinks.linkToOrders("orders"));

		if (apiSecurity.isAllowedToSearchUsersGroupsPermissions()) {
			rootEntryPointModel.add(userLinks.linkToUsers("users"));
			rootEntryPointModel.add(groupLinks.linkToGroups("groups"));
			rootEntryPointModel.add(permissionLinks.linkToPermissions("permissions"));
		}

		if (apiSecurity.isAllowedToSearchStatistics())
			rootEntryPointModel.add(statisticLinks.linkToStatistics("statistics"));

		return rootEntryPointModel;
	}

	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {}

}
