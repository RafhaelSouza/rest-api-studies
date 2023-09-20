package com.studies.foodorders.api.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.model.paymentway.PaymentWayInput;
import com.studies.foodorders.api.model.paymentway.PaymentWayModel;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Payment Way")
public interface PaymentWayControllerOpenApi {

    @ApiOperation("List of payment ways")
    ResponseEntity<List<PaymentWayModel>> list(ServletWebRequest request);

    @ApiOperation("Find a payment way by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid payment way id", response = ApiError.class),
            @ApiResponse(code = 404, message = "Payment way not found", response = ApiError.class)
    })
    ResponseEntity<PaymentWayModel> find(@ApiParam(value = "Payment way id", example = "1") Long id, ServletWebRequest request);

    @ApiOperation("Register a payment way")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Registered payment way")
    })
    PaymentWayModel save(@ApiParam(name = "Request body", value = "Representation of a new payment way") PaymentWayInput paymentWayInput);

    @ApiOperation("Update a payment way")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated  payment way"),
            @ApiResponse(code = 404, message = "Payment way not found", response = ApiError.class)
    })
    PaymentWayModel update(@ApiParam(value = "Payment way id", example = "1") Long id,
                           @ApiParam(name = "Request body", value = "Representation of a new payment way") PaymentWayInput paymentWayInput);

    @ApiOperation("Delete a payment way")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Deleted payment way"),
            @ApiResponse(code = 404, message = "Payment way not found", response = ApiError.class)
    })
    void delete(@ApiParam(value = "Payment way id", example = "1") Long id);

}
