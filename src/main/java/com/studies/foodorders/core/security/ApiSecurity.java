package com.studies.foodorders.core.security;

import com.studies.foodorders.domain.repositories.order.OrderRepository;
import com.studies.foodorders.domain.repositories.restaurant.RestaurantRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ApiSecurity {

	private final RestaurantRepository restaurantRepository;

	private final OrderRepository orderRepository;

	public ApiSecurity(RestaurantRepository restaurantRepository, OrderRepository orderRepository) {
		this.restaurantRepository = restaurantRepository;
		this.orderRepository = orderRepository;
	}

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public boolean isAuthenticated() {
		return getAuthentication().isAuthenticated();
	}
	
	public Long getUserId() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		
		return jwt.getClaim("user_id");
	}

	public boolean manageRestaurant(Long restaurantId) {

		if (restaurantId == null)
			return false;

		return restaurantRepository.isRestaurantResponsible(restaurantId, getUserId());
	}

	public boolean manageOrderRestaurant(String orderCode) {
		return orderRepository.isOrderManagedBy(UUID.fromString(orderCode), getUserId());
	}

	public boolean authenticatedUserEquals(Long userId) {
		return getUserId() != null && userId != null
				&& getUserId().equals(userId);
	}

	public boolean manageOrder(String orderCode) {
		return hasWriteScope() && (hasAuthority("MANAGE_ORDERS")
				|| manageOrderRestaurant(orderCode));
	}

	public boolean hasAuthority(String authorityName) {
		return getAuthentication().getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals(authorityName));
	}

	public boolean hasWriteScope() {
		return hasAuthority("SCOPE_WRITE");
	}

	public boolean hasReadScope() {
		return hasAuthority("SCOPE_READ");
	}

	public boolean isAllowedToSearchRestaurants() {
		return hasReadScope() && isAuthenticated();
	}

	public boolean isAllowedToManageRestaurant() {
		return hasWriteScope() && hasAuthority("UPDATE_RESTAURANTS");
	}

	public boolean isAllowedToManageRestaurantOperation(Long restaurantId) {
		return hasWriteScope() && (hasAuthority("UPDATE_RESTAURANTS")
				|| manageRestaurant(restaurantId));
	}

	public boolean isAllowedToSearchUsersGroupsPermissions() {
		return hasReadScope() && hasAuthority("SEARCH_USERS_GROUPS_PERMISSIONS");
	}

	public boolean isAllowedToUpdateUsersGroupsPermissions() {
		return hasWriteScope() && hasAuthority("UPDATE_USERS_GROUPS_PERMISSIONS");
	}

	public boolean isAllowedToSearchOrders(Long clientId, Long restaurantId) {
		return hasReadScope() && (hasAuthority("SEARCH_ORDERS")
				|| authenticatedUserEquals(clientId) || manageRestaurant(restaurantId));
	}

	public boolean isAllowedToSearchOrders() {
		return isAuthenticated() && hasReadScope();
	}

	public boolean isAllowedToSearchPaymentWays() {
		return isAuthenticated() && hasReadScope();
	}

	public boolean isAllowedToSearchCities() {
		return isAuthenticated() && hasReadScope();
	}

	public boolean isAllowedToSearchStates() {
		return isAuthenticated() && hasReadScope();
	}

	public boolean isAllowedToSearchKitchens() {
		return isAuthenticated() && hasReadScope();
	}

	public boolean isAllowedToSearchStatistics() {
		return hasReadScope() && hasAuthority("GENERATE_REPORTS");
	}


}
