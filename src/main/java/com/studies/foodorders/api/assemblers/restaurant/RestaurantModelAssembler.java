package com.studies.foodorders.api.assemblers.restaurant;

import com.studies.foodorders.api.controllers.restaurant.RestaurantController;
import com.studies.foodorders.api.links.CityLinks;
import com.studies.foodorders.api.links.KitchenLinks;
import com.studies.foodorders.api.links.RestaurantLinks;
import com.studies.foodorders.api.model.restaurant.RestaurantModel;
import com.studies.foodorders.api.model.restaurant.input.RestaurantInput;
import com.studies.foodorders.domain.models.kitchen.Kitchen;
import com.studies.foodorders.domain.models.localization.City;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestaurantModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestaurantLinks restaurantLinks;

    @Autowired
    private KitchenLinks kitchenLinks;

    @Autowired
    private CityLinks cityLinks;

    public RestaurantModelAssembler() {
        super(RestaurantController.class, RestaurantModel.class);
    }

    @Override
    public RestaurantModel toModel(Restaurant restaurant) {

        RestaurantModel restaurantModel = createModelWithId(restaurant.getId(), restaurant);
        modelMapper.map(restaurant, restaurantModel);

        restaurantModel.add(restaurantLinks.linkToRestaurants("restaurants"));

        if (restaurant.activationAllowed())
            restaurantModel.add(restaurantLinks.linkToRestaurantActivation(restaurant.getId(), "activate"));

        if (restaurant.inactivationAllowed())
            restaurantModel.add(restaurantLinks.linkToRestaurantInactivation(restaurant.getId(), "inactivate"));

        if (restaurant.openingAllowed())
            restaurantModel.add(restaurantLinks.linkToRestaurantOpening(restaurant.getId(), "open"));

        if (restaurant.closingAllowed())
            restaurantModel.add(restaurantLinks.linkToRestaurantClosing(restaurant.getId(), "close"));

        restaurantModel.getKitchen().add(
                kitchenLinks.linkToKitchen(restaurant.getKitchen().getId()));

        if (restaurantModel.getAddress() != null && restaurantModel.getAddress().getCity() != null)
            restaurantModel.getAddress().getCity().add(
                cityLinks.linkToCity(restaurant.getAddress().getCity().getId()));

        restaurantModel.add(restaurantLinks.linkToRestaurantPaymentWays(restaurant.getId(),"payment-ways"));

        restaurantModel.add(restaurantLinks.linkToRestaurantResponsible(restaurant.getId(),"responsible"));

        return restaurantModel;
    }

    @Override
    public CollectionModel<RestaurantModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
        return super.toCollectionModel(entities)
                .add(restaurantLinks.linkToRestaurants());
    }

    public Restaurant toDomainObject(RestaurantInput restaurantInput) {
        return modelMapper.map(restaurantInput, Restaurant.class);
    }

    public void copyToDomainObject(RestaurantInput restaurantInput, Restaurant restaurant) {
        restaurant.setKitchen(new Kitchen());

        if (restaurant.getAddress() != null) {
            restaurant.getAddress().setCity(new City());
        }

        modelMapper.map(restaurantInput, restaurant);
    }

}
