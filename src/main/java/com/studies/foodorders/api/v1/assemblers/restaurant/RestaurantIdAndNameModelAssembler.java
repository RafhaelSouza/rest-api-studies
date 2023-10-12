package com.studies.foodorders.api.v1.assemblers.restaurant;

import com.studies.foodorders.api.v1.controllers.restaurant.RestaurantController;
import com.studies.foodorders.api.v1.links.RestaurantLinks;
import com.studies.foodorders.api.v1.models.restaurant.RestaurantIdAndNameModel;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestaurantIdAndNameModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantIdAndNameModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestaurantLinks restaurantLinks;

    public RestaurantIdAndNameModelAssembler() {
        super(RestaurantController.class, RestaurantIdAndNameModel.class);
    }

    @Override
    public RestaurantIdAndNameModel toModel(Restaurant restaurant) {

        RestaurantIdAndNameModel restaurantModel = createModelWithId(restaurant.getId(), restaurant);

        modelMapper.map(restaurant, restaurantModel);

        restaurantModel.add(restaurantLinks.linkToRestaurants("restaurants"));

        return restaurantModel;

    }

    @Override
    public CollectionModel<RestaurantIdAndNameModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
        return super.toCollectionModel(entities)
                .add(restaurantLinks.linkToRestaurants());
    }

}
