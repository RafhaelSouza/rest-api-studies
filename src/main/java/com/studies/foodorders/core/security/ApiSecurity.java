package com.studies.foodorders.core.security;

import com.studies.foodorders.domain.repositories.restaurant.RestaurantRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class ApiSecurity {

	private final RestaurantRepository restaurantRepository;

	public ApiSecurity(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
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
	
}
