package com.studies.foodorders.api.v1.assemblers.restaurant;

import com.studies.foodorders.api.v1.controllers.restaurant.RestaurantController;
import com.studies.foodorders.api.v1.links.KitchenLinks;
import com.studies.foodorders.api.v1.links.RestaurantLinks;
import com.studies.foodorders.api.v1.models.restaurant.RestaurantBasicModel;
import com.studies.foodorders.core.security.ApiSecurity;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestaurantBasicModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantBasicModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestaurantLinks restaurantLinks;

    @Autowired
    private KitchenLinks kitchenLinks;

    @Autowired
    private ApiSecurity apiSecurity;

    public RestaurantBasicModelAssembler() {
        super(RestaurantController.class, RestaurantBasicModel.class);
    }

    @Override
    public RestaurantBasicModel toModel(Restaurant restaurant) {

        RestaurantBasicModel restaurantModel = createModelWithId(restaurant.getId(), restaurant);

        modelMapper.map(restaurant, restaurantModel);

        if (apiSecurity.isAllowedToSearchRestaurants())
            restaurantModel.add(restaurantLinks.linkToRestaurants("restaurants"));

        if (apiSecurity.isAllowedToSearchKitchens())
            restaurantModel.getKitchen().add(kitchenLinks.linkToKitchen(restaurant.getKitchen().getId()));

        return restaurantModel;

    }

    @Override
    public CollectionModel<RestaurantBasicModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
        CollectionModel<RestaurantBasicModel> restaurantBasicModelCollectionModel = super.toCollectionModel(entities);

        if (apiSecurity.isAllowedToSearchRestaurants())
            restaurantBasicModelCollectionModel.add(restaurantLinks.linkToRestaurants());

        return restaurantBasicModelCollectionModel;
    }

}
