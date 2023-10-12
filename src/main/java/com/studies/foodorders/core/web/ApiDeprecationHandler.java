package com.studies.foodorders.core.web;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class ApiDeprecationHandler implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (request.getRequestURI().startsWith("/v1/cities")
				|| request.getRequestURI().startsWith("/v1/kitchens"))
			response.addHeader("X-Api-Deprecated",
					"This version of the cities and kitchens API is deprecated and will be available up to mm/dd/YYYY."
							+ "Use the most current version of the API.");

		return true;
	}

}
