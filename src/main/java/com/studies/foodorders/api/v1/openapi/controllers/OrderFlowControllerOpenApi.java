package com.studies.foodorders.api.v1.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

@Api(tags = "Orders")
public interface OrderFlowControllerOpenApi {

    @ApiOperation("Order confirmation")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Order confirmed successfully"),
            @ApiResponse(code = 404, message = "Order not found", response = ApiError.class)
    })
    ResponseEntity<Void> confirm(@ApiParam(value = "Order id", example = "cee92b85-1456-43d7-842f-93be0d57b954", required = true) String orderCode);

    @ApiOperation("Order cancellation")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Order canceled successfully"),
            @ApiResponse(code = 404, message = "Order not found", response = ApiError.class)
    })
    ResponseEntity<Void> cancel(@ApiParam(value = "Order id", example = "cee92b85-1456-43d7-842f-93be0d57b954", required = true) String orderCode);

    @ApiOperation("Register order delivery")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Order delivery successfully registered"),
            @ApiResponse(code = 404, message = "Order not found", response = ApiError.class)
    })
    ResponseEntity<Void> deliver(@ApiParam(value = "Order id", example = "cee92b85-1456-43d7-842f-93be0d57b954", required = true) String orderCode);

}
