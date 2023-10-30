package com.studies.foodorders.core.security;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {

	@interface Kitchens {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('UPDATE_KITCHEN')")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToUpdate { }

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToSearch { }
		
	}

	@interface Restaurants {

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('UPDATE_RESTAURANTS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToManageRestaurant { }

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and "
				+ "(hasAuthority('UPDATE_RESTAURANTS') or "
				+ "@apiSecurity.manageRestaurant(#restaurantId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToManageRestaurantOperation { }

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToSearch { }

	}

	@interface Orders {

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@PostAuthorize("hasAuthority('SEARCH_ORDERS') or "
				+ "@apiSecurity.getUserId() == returnObject.client.id or "
				+ "@apiSecurity.manageRestaurant(returnObject.restaurant.id)")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToFind { }

	}
	
}
