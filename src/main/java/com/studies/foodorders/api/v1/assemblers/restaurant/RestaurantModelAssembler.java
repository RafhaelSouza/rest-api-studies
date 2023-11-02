package com.studies.foodorders.api.v1.assemblers.restaurant;

import com.studies.foodorders.api.v1.controllers.restaurant.RestaurantController;
import com.studies.foodorders.api.v1.links.CityLinks;
import com.studies.foodorders.api.v1.links.KitchenLinks;
import com.studies.foodorders.api.v1.links.ProductLinks;
import com.studies.foodorders.api.v1.links.RestaurantLinks;
import com.studies.foodorders.api.v1.models.restaurant.RestaurantModel;
import com.studies.foodorders.api.v1.models.restaurant.input.RestaurantInput;
import com.studies.foodorders.core.security.ApiSecurity;
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

    @Autowired
    private ProductLinks productLinks;

    @Autowired
    private ApiSecurity apiSecurity;

    public RestaurantModelAssembler() {
        super(RestaurantController.class, RestaurantModel.class);
    }

    @Override
    public RestaurantModel toModel(Restaurant restaurant) {

        RestaurantModel restaurantModel = createModelWithId(restaurant.getId(), restaurant);
        modelMapper.map(restaurant, restaurantModel);

        if (apiSecurity.isAllowedToSearchRestaurants())
            restaurantModel.add(restaurantLinks.linkToRestaurants("restaurants"));

        if (apiSecurity.isAllowedToManageRestaurant()) {
            if (restaurant.activationAllowed())
                restaurantModel.add(restaurantLinks.linkToRestaurantActivation(restaurant.getId(), "activate"));

            if (restaurant.inactivationAllowed())
                restaurantModel.add(restaurantLinks.linkToRestaurantInactivation(restaurant.getId(), "inactivate"));
        }

        if (apiSecurity.isAllowedToManageRestaurantOperation(restaurant.getId())) {
            if (restaurant.openingAllowed())
                restaurantModel.add(restaurantLinks.linkToRestaurantOpening(restaurant.getId(), "open"));

            if (restaurant.closingAllowed())
                restaurantModel.add(restaurantLinks.linkToRestaurantClosing(restaurant.getId(), "close"));
        }

        if (apiSecurity.isAllowedToSearchRestaurants())
            restaurantModel.add(productLinks.linkToProducts(restaurant.getId(), "products"));

        if (apiSecurity.isAllowedToSearchKitchens())
            restaurantModel.getKitchen().add(kitchenLinks.linkToKitchen(restaurant.getKitchen().getId()));

        if (apiSecurity.isAllowedToSearchCities()) {
            if (restaurantModel.getAddress() != null && restaurantModel.getAddress().getCity() != null)
                restaurantModel.getAddress().getCity().add(cityLinks.linkToCity(restaurant.getAddress().getCity().getId()));
        }

        if (apiSecurity.isAllowedToSearchRestaurants())
            restaurantModel.add(restaurantLinks.linkToRestaurantPaymentWays(restaurant.getId(),"payment-ways"));

        if (apiSecurity.isAllowedToManageRestaurant())
            restaurantModel.add(restaurantLinks.linkToRestaurantResponsible(restaurant.getId(),"responsible"));

        return restaurantModel;
    }

    @Override
    public CollectionModel<RestaurantModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
        CollectionModel<RestaurantModel> restaurantModelCollectionModel = super.toCollectionModel(entities);

        if (apiSecurity.isAllowedToSearchRestaurants())
            restaurantModelCollectionModel.add(restaurantLinks.linkToRestaurants());

        return restaurantModelCollectionModel;
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
