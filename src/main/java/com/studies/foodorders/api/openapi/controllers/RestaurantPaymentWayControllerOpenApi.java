package com.studies.foodorders.api.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.model.paymentway.PaymentWayModel;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Restaurants")
public interface RestaurantPaymentWayControllerOpenApi {

    @ApiOperation("List payment methods associated with the restaurant")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Restaurant not found", response = ApiError.class)
    })
    CollectionModel<PaymentWayModel> list(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId);

    @ApiOperation("Restaurant association with payment method")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Association done successfully"),
            @ApiResponse(code = 404, message = "Restaurant or payment method not found",
                    response = ApiError.class)
    })
    void associate(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId,
                   @ApiParam(value = "Payment Way id", example = "1", required = true) Long paymentWayId);

    @ApiOperation("Disassociation of restaurant with payment method")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Disassociation done successfully"),
            @ApiResponse(code = 404, message = "Restaurant or payment method not found",
                    response = ApiError.class)
    })
    void disassociate(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId,
                      @ApiParam(value = "Payment Way id", example = "1", required = true) Long paymentWayId);

}
