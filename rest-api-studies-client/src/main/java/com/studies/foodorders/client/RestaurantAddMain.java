package com.studies.foodorders.client;

import com.studies.foodorders.client.api.ClientApiException;
import com.studies.foodorders.client.api.RestaurantClient;
import com.studies.foodorders.client.model.RestaurantModel;
import com.studies.foodorders.client.model.input.AddressInput;
import com.studies.foodorders.client.model.input.CityIdInput;
import com.studies.foodorders.client.model.input.KitchenIdInput;
import com.studies.foodorders.client.model.input.RestaurantInput;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class RestaurantAddMain {

	public static void main(String[] args) {
		try {
			var restTemplate = new RestTemplate();
			var restaurantClient = new RestaurantClient(
					restTemplate, "http://localhost:8080");

			var kitchen = new KitchenIdInput();
			kitchen.setId(1L);

			var city = new CityIdInput();
			city.setId(1L);

			var address = new AddressInput();
			address.setCity(city);
			address.setPostalCode("38500-111");
			address.setStreet("Xyz Street");
			address.setNumber("300");
			address.setDistrict("Downtown");

			var restaurant = new RestaurantInput();
			restaurant.setName("Some food");
			restaurant.setShippingCosts(BigDecimal.valueOf(9.5));
			restaurant.setKitchen(new KitchenIdInput());
			restaurant.setKitchen(kitchen);
			restaurant.setAddress(address);

			RestaurantModel restaurantModel = restaurantClient.add(restaurant);

			System.out.println(restaurantModel);
		} catch (ClientApiException e) {
			if (e.getProblem() != null) {
				System.out.println(e.getProblem().getUserMessage());
				
				e.getProblem().getObjects().stream()
					.forEach(p -> System.out.println("- " + p.getUserMessage()));
				
			} else {
				System.out.println("Unknown error");
				e.printStackTrace();
			}
		}
	}

}