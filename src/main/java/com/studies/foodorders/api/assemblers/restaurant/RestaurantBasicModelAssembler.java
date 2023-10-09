package com.studies.foodorders.api.assemblers.restaurant;

import com.studies.foodorders.api.controllers.restaurant.RestaurantController;
import com.studies.foodorders.api.links.KitchenLinks;
import com.studies.foodorders.api.links.RestaurantLinks;
import com.studies.foodorders.api.model.restaurant.RestaurantBasicModel;
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

    public RestaurantBasicModelAssembler() {
        super(RestaurantController.class, RestaurantBasicModel.class);
    }

    @Override
    public RestaurantBasicModel toModel(Restaurant restaurant) {

        RestaurantBasicModel restaurantModel = createModelWithId(restaurant.getId(), restaurant);

        modelMapper.map(restaurant, restaurantModel);

        restaurantModel.add(restaurantLinks.linkToRestaurants("restaurants"));

        restaurantModel.getKitchen().add(kitchenLinks.linkToKitchen(restaurant.getKitchen().getId()));

        return restaurantModel;

    }

    @Override
    public CollectionModel<RestaurantBasicModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
        return super.toCollectionModel(entities)
                .add(restaurantLinks.linkToRestaurants());
    }

}
