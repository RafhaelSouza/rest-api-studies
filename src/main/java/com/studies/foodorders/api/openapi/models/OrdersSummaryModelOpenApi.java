package com.studies.foodorders.api.openapi.models;

import com.studies.foodorders.api.model.order.OrderSummaryModel;
import io.swagger.annotations.ApiModel;

@ApiModel("OrdersSummaryModel")
public class OrdersSummaryModelOpenApi extends PagedModelOpenApi<OrderSummaryModel> {}