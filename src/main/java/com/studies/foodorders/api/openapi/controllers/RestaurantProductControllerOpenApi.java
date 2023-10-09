package com.studies.foodorders.api.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.model.product.ProductInput;
import com.studies.foodorders.api.model.product.ProductModel;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Products")
public interface RestaurantProductControllerOpenApi {

    @ApiOperation("List of products")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid restaurant id", response = ApiError.class),
            @ApiResponse(code = 404, message = "Restaurant not found", response = ApiError.class)
    })
    CollectionModel<ProductModel> list(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId,
                                       @ApiParam(value = "Indicates whether or not to include inactive products in the listing result",
                                    example = "false", defaultValue = "false") Boolean includeInactives);

    @ApiOperation("Find a product by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid restaurant or product id", response = ApiError.class),
            @ApiResponse(code = 404, message = "Restaurant product not found", response = ApiError.class)
    })
    ProductModel find(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId,
                      @ApiParam(value = "Product id", example = "1", required = true) Long productId);

    @ApiOperation("Register a restaurant product")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Registered product")
    })
    ProductModel save(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId,
                      @ApiParam(name = "Request body", value = "Representation of a new product", required = true) ProductInput productInput);

    @ApiOperation("Update a restaurant product")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated product"),
            @ApiResponse(code = 404, message = "Restaurant product not found", response = ApiError.class)
    })
    ProductModel update(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId,
                        @ApiParam(value = "Product id", example = "1", required = true) Long productId,
                        @ApiParam(name = "Request Body", value = "Representation of a product with the new values", required = true) ProductInput productInput);

}
