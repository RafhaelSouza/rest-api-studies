package com.studies.foodorders.api.v1.controllers.restaurant;

import com.studies.foodorders.api.v1.assemblers.security.UserModelAssembler;
import com.studies.foodorders.api.v1.links.RestaurantLinks;
import com.studies.foodorders.api.v1.models.security.user.UserModel;
import com.studies.foodorders.api.v1.openapi.controllers.RestaurantResponsibleUserControllerOpenApi;
import com.studies.foodorders.core.security.ApiSecurity;
import com.studies.foodorders.core.security.CheckSecurity;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.services.restaurant.RestaurantService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/restaurants/{restaurantId}/responsible")
public class RestaurantResponsibleUserController implements RestaurantResponsibleUserControllerOpenApi {

    private RestaurantService restaurantService;

    private UserModelAssembler userModelAssembler;

    private RestaurantLinks restaurantLinks;

    private ApiSecurity apiSecurity;

    public RestaurantResponsibleUserController(RestaurantService restaurantService,
                                               UserModelAssembler userModelAssembler,
                                               RestaurantLinks restaurantLinks,
                                               ApiSecurity apiSecurity) {
        this.restaurantService = restaurantService;
        this.userModelAssembler = userModelAssembler;
        this.restaurantLinks = restaurantLinks;
        this.apiSecurity = apiSecurity;
    }

    @CheckSecurity.Restaurants.AllowToManageRestaurant
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<UserModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findIfExists(restaurantId);

        CollectionModel<UserModel> usersModel = userModelAssembler.toCollectionModel(restaurant.getResponsible())
                .removeLinks();

        usersModel.add(restaurantLinks.linkToRestaurantResponsible(restaurantId));


        if (apiSecurity.isAllowedToManageRestaurant()) {
            usersModel.add(restaurantLinks.linkToRestaurantResponsibleAssociation(restaurantId, "associate"));

            usersModel.getContent().stream().forEach(userModel -> {
                userModel.add(restaurantLinks.linkToRestaurantResponsibleDisassociation(
                        restaurantId, userModel.getId(), "disassociate"));
            });
        }

        return usersModel;
    }

    @CheckSecurity.Restaurants.AllowToManageRestaurantOperation
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.associateResponsible(restaurantId, userId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.AllowToManageRestaurantOperation
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disassociate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.disassociateResponsible(restaurantId, userId);

        return ResponseEntity.noContent().build();
    }

}
