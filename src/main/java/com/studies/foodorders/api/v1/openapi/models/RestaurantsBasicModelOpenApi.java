package com.studies.foodorders.api.v1.openapi.models;

import com.studies.foodorders.api.v1.models.restaurant.RestaurantBasicModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("RestaurantsBasicoModel")
@Data
public class RestaurantsBasicModelOpenApi {

    private RestaurantsEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("RestaurantsEmbeddedModel")
    @Data
    public class RestaurantsEmbeddedModelOpenApi {

        private List<RestaurantBasicModel> restaurants;

    }

}
