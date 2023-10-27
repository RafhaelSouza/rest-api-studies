package com.studies.foodorders.core.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {

	@interface Kitchens {
		
		@PreAuthorize("hasAuthority('UPDATE_KITCHEN')")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowUpdate { }

		@PreAuthorize("isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		@interface AllowSearch { }
		
	}
	
}
