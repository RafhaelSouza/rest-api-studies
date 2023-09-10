package com.studies.foodorders.client.api;

import com.studies.foodorders.client.model.RestaurantModel;
import com.studies.foodorders.client.model.RestaurantSummaryModel;
import com.studies.foodorders.client.model.input.RestaurantInput;
import lombok.AllArgsConstructor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class RestaurantClient {

	private static final String RESOURCE_PATH = "/restaurants";
	
	private RestTemplate restTemplate;
	private String url;
	
	public List<RestaurantSummaryModel> list() {
		URI resourceUri = URI.create(url + RESOURCE_PATH);
		
		RestaurantSummaryModel[] restaurants = restTemplate
				.getForObject(resourceUri, RestaurantSummaryModel[].class);
		
		return Arrays.asList(restaurants);
	}

	public RestaurantModel add(RestaurantInput restaurant) {
		var resourceUri = URI.create(url + RESOURCE_PATH);

		try {
			return restTemplate
					.postForObject(resourceUri, restaurant, RestaurantModel.class);
		} catch (HttpClientErrorException e) {
			throw new ClientApiException(e.getMessage(), e);
		}
	}
	
}
