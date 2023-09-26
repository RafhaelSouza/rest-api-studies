package com.studies.foodorders.api.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import io.swagger.annotations.*;

@Api(tags = "Orders")
public interface OrderFlowControllerOpenApi {

    @ApiOperation("Order confirmation")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Order confirmed successfully"),
            @ApiResponse(code = 404, message = "Order not found", response = ApiError.class)
    })
    void confirm(@ApiParam(value = "Order id", example = "cee92b85-1456-43d7-842f-93be0d57b954", required = true) String orderCode);

    @ApiOperation("Order cancellation")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Order canceled successfully"),
            @ApiResponse(code = 404, message = "Order not found", response = ApiError.class)
    })
    void cancel(@ApiParam(value = "Order id", example = "cee92b85-1456-43d7-842f-93be0d57b954", required = true) String orderCode);

    @ApiOperation("Register order delivery")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Order delivery successfully registered"),
            @ApiResponse(code = 404, message = "Order not found", response = ApiError.class)
    })
    void deliver(@ApiParam(value = "Order id", example = "cee92b85-1456-43d7-842f-93be0d57b954", required = true) String orderCode);

}
