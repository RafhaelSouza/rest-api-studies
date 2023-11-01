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

		@PreAuthorize("@apiSecurity.isAllowedToSearchKitchens()")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToSearch { }
		
	}

	@interface Restaurants {

		@PreAuthorize("@apiSecurity.isAllowedToSearchRestaurants()")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToManageRestaurant { }

		@PreAuthorize("@apiSecurity.isAllowedToManageRestaurantOperation(#restaurantId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToManageRestaurantOperation { }

		@PreAuthorize("@apiSecurity.isAllowedToSearchRestaurants()")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToSearch { }

	}

	@interface Orders {

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@PostAuthorize("hasAuthority('SEARCH_ORDERS') or "
				+ "@apiSecurity.authenticatedUserEquals(returnObject.client.id) or "
				+ "@apiSecurity.manageRestaurant(returnObject.restaurant.id)")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToFind { }

		@PreAuthorize("@apiSecurity.isAllowedToSearchOrders(#filters.clientId, #filters.restaurantId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToSearch { }

		@PreAuthorize("@apiSecurity.manageOrder(#orderCode)")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToManageOrders { }

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToAdd { }

	}

	@interface PaymentWays {

		@PreAuthorize("@apiSecurity.isAllowedToSearchPaymentWays()")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToSearch { }

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('UPDATE_PAYMENT_WAYS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToUpdate { }

	}

	@interface Cities {

		@PreAuthorize("@apiSecurity.isAllowedToSearchCities()")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToSearch { }

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('UPDATE_CITIES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToUpdate { }

	}

	@interface States {

		@PreAuthorize("@apiSecurity.isAllowedToSearchStates()")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToSearch { }

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('UPDATE_STATES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToUpdate { }

	}

	@interface UsersGroupsPermissions {

		@PreAuthorize("@apiSecurity.isAllowedToSearchUsersGroupsPermissions()")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToSearch { }

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and @apiSecurity.authenticatedUserEquals(#userId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToUpdateOwnPassword { }

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('UPDATE_USERS_GROUPS_PERMISSIONS') or "
				+ "@apiSecurity.authenticatedUserEquals(#userId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToUpdateUser { }

		@PreAuthorize("@apiSecurity.isAllowedToUpdateUsersGroupsPermissions()")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToUpdate { }

	}

	@interface Statistics {

		@PreAuthorize("@apiSecurity.isAllowedToSearchStatistics()")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToSearch { }

	}
	
}
