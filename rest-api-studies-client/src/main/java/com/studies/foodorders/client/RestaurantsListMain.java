package com.studies.foodorders.client;

import com.studies.foodorders.client.api.ClientApiException;
import com.studies.foodorders.client.api.RestaurantClient;
import org.springframework.web.client.RestTemplate;

public class RestaurantsListMain {

	public static void main(String[] args) {
		try {
			RestTemplate restTemplate = new RestTemplate();

			RestaurantClient restaurantClient = new RestaurantClient(
					restTemplate, "http://localhost:8080");

			restaurantClient.list().stream()
					.forEach(restaurant -> System.out.println(restaurant));
		} catch (ClientApiException e) {
			if (e.getProblem() != null) {
//				System.out.println(e.getProblem());
				System.out.println(e.getProblem().getUserMessage());
			} else {
				System.out.println("Unknown error");
				e.printStackTrace();
			}
		}
	}
	
}
