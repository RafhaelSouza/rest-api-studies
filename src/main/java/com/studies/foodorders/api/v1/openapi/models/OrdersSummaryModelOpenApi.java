package com.studies.foodorders.api.v1.openapi.models;

import com.studies.foodorders.api.v1.models.order.OrderSummaryModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("OrdersSummaryModel")
@Setter
@Getter
public class OrdersSummaryModelOpenApi {

    private OrdersSummaryEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @ApiModel("OrdersSummaryEmbeddedModel")
    @Data
    public class OrdersSummaryEmbeddedModelOpenApi {

        private List<OrderSummaryModel> orders;

    }

}