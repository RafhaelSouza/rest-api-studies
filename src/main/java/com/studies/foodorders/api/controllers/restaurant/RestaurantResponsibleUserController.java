package com.studies.foodorders.api.controllers.restaurant;

import com.studies.foodorders.api.assemblers.security.UserModelAssembler;
import com.studies.foodorders.api.model.security.user.UserModel;
import com.studies.foodorders.api.openapi.controllers.RestaurantResponsibleUserControllerOpenApi;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.services.restaurant.RestaurantService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/responsible")
public class RestaurantResponsibleUserController implements RestaurantResponsibleUserControllerOpenApi {

    private RestaurantService restaurantService;

    private UserModelAssembler userModelAssembler;

    public RestaurantResponsibleUserController(RestaurantService restaurantService, UserModelAssembler userModelAssembler) {
        this.restaurantService = restaurantService;
        this.userModelAssembler = userModelAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<UserModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findIfExists(restaurantId);

        return userModelAssembler.toCollectionModel(restaurant.getResponsible());
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
