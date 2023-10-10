package com.studies.foodorders.api.v1.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.v1.models.order.OrderInput;
import com.studies.foodorders.api.v1.models.order.OrderModel;
import com.studies.foodorders.api.v1.models.order.OrderSummaryModel;
import com.studies.foodorders.domain.filter.OrderFilter;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Orders")
public interface OrderControllerOpenApi {

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Names of properties to filter on the response, separated by commas",
                    name = "fields", paramType = "query", type = "string")
    })
    @ApiOperation("Search of orders")
    PagedModel<OrderSummaryModel> searchBy(Pageable pageable, OrderFilter filters);

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Names of properties to filter on the response, separated by commas",
                    name = "fields", paramType = "query", type = "string")
    })
    @ApiOperation("Find a order by id")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Order not found", response = ApiError.class)
    })
    OrderModel find(@ApiParam(value = "Order id", example = "cee92b85-1456-43d7-842f-93be0d57b954", required = true) String orderCode);

    @ApiOperation("Register an order")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Registered order")
    })
    OrderModel add(@ApiParam(name = "Request body", value = "Representation of a new order", required = true) OrderInput orderInput);

}
