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

		@PreAuthorize("hasAuthority('SCOPE_READ') and (hasAuthority('SEARCH_ORDERS') or "
				+ "@apiSecurity.getUserId() == #filters.clientId or"
				+ "@apiSecurity.manageRestaurant(#filters.restaurantId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToSearch { }

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('MANAGE_ORDERS') or "
				+ "@apiSecurity.manageOrderRestaurant(#orderCode))")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToManageOrders { }

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToAdd { }

	}

	@interface PaymentWays {

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToSearch { }

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('UPDATE_PAYMENT_WAYS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToUpdate { }

	}

	@interface Cities {

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToSearch { }

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('UPDATE_CITIES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToUpdate { }

	}

	@interface States {

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToSearch { }

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('UPDATE_STATES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToUpdate { }

	}

	@interface UsersGroupsPermissions {

		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('SEARCH_USERS_GROUPS_PERMISSIONS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToSearch { }

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and "
				+ "@apiSecurity.getUserId() == #userId")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToUpdateOwnPassword { }

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('UPDATE_USERS_GROUPS_PERMISSIONS') or "
				+ "@apiSecurity.getUserId() == #userId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToUpdateUser { }

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('UPDATE_USERS_GROUPS_PERMISSIONS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToUpdate { }

	}

	@interface Statistics {

		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('GENERATE_REPORTS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowToSearch { }

	}
	
}
