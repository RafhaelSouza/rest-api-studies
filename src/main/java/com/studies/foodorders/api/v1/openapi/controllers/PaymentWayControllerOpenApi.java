package com.studies.foodorders.api.v1.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.v1.models.paymentway.PaymentWayInput;
import com.studies.foodorders.api.v1.models.paymentway.PaymentWayModel;
import com.studies.foodorders.api.v1.openapi.models.PaymentWaysModelOpenApi;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

@Api(tags = "Payment Ways")
public interface PaymentWayControllerOpenApi {

    @ApiOperation(value = "List of payment ways")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = PaymentWaysModelOpenApi.class)
    })
    ResponseEntity<CollectionModel<PaymentWayModel>> list(ServletWebRequest request);

    @ApiOperation("Find a payment way by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid payment way id", response = ApiError.class),
            @ApiResponse(code = 404, message = "Payment way not found", response = ApiError.class)
    })
    ResponseEntity<PaymentWayModel> find(@ApiParam(value = "Payment way id", example = "1", required = true) Long id,
                                         ServletWebRequest request);

    @ApiOperation("Register a payment way")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Registered payment way")
    })
    PaymentWayModel save(@ApiParam(name = "Request body", value = "Representation of a new payment way", required = true) PaymentWayInput paymentWayInput);

    @ApiOperation("Update a payment way")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated  payment way"),
            @ApiResponse(code = 404, message = "Payment way not found", response = ApiError.class)
    })
    PaymentWayModel update(@ApiParam(value = "Payment way id", example = "1", required = true) Long id,
                           @ApiParam(name = "Request body", value = "Representation of a new payment way", required = true) PaymentWayInput paymentWayInput);

    @ApiOperation("Delete a payment way")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Deleted payment way"),
            @ApiResponse(code = 404, message = "Payment way not found", response = ApiError.class)
    })
    void delete(@ApiParam(value = "Payment way id", example = "1", required = true) Long id);

}
