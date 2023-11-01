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
	
	public Long getUserId() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		
		return jwt.getClaim("user_id");
	}

	public boolean manageRestaurant(Long restaurantId) {
		return restaurantRepository.isRestaurantResponsible(restaurantId, getUserId());
	}

	public boolean manageOrderRestaurant(String orderCode) {
		return orderRepository.isOrderManagedBy(UUID.fromString(orderCode), getUserId());
	}

	public boolean authenticatedUserEquals(Long userId) {
		return getUserId() != null && userId != null
				&& getUserId().equals(userId);
	}
	
}
