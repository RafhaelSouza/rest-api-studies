package com.studies.foodorders.api.v2.openapi.models;

import com.studies.foodorders.api.v2.models.kitchen.KitchenModelV2;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("KitchensModel")
@Setter
@Getter
public class KitchensModelV2OpenApi {

    private KitchenEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelV2OpenApi page;

    @ApiModel("KitchensEmbeddedModel")
    @Data
    public class KitchenEmbeddedModelOpenApi {

        private List<KitchenModelV2> kitchens;

    }

}