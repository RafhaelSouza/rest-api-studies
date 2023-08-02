package com.studies.foodorders.api.controllers.restaurant;

import com.studies.foodorders.api.converter.security.UserModelConverter;
import com.studies.foodorders.api.model.security.user.UserModel;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.services.restaurant.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/responsible")
public class RestaurantResponsibleUserController {

    private RestaurantService restaurantService;

    private UserModelConverter userModelConverter;

    public RestaurantResponsibleUserController(RestaurantService restaurantService, UserModelConverter userModelConverter) {
        this.restaurantService = restaurantService;
        this.userModelConverter = userModelConverter;
    }

    @GetMapping
    public List<UserModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findIfExists(restaurantId);

        return userModelConverter.toCollectionModel(restaurant.getResponsible());
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.associateResponsible(restaurantId, userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.disassociateResponsible(restaurantId, userId);
    }

}
