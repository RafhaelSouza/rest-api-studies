package com.studies.foodorders.api.openapi.models;

import com.studies.foodorders.api.model.kitchen.KitchenModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("KitchensModel")
@Setter
@Getter
public class KitchensModelOpenApi {

    private KitchenEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @ApiModel("KitchensEmbeddedModel")
    @Data
    public class KitchenEmbeddedModelOpenApi {

        private List<KitchenModel> kitchens;

    }

}