package com.studies.foodorders.api.openapi.controllers;

import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.model.product.ProductPhotoInput;
import com.studies.foodorders.api.model.product.ProductPhotoModel;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "Products")
public interface RestaurantProductPhotoControllerOpenApi {

    @ApiOperation(value = "Find a photo of a restaurant’s product", produces = "application/json, image/jpeg, image/png")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid restaurant or product id", response = ApiError.class),
            @ApiResponse(code = 404, message = "Product photo not found", response = ApiError.class)
    })
    ProductPhotoModel find(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId,
                           @ApiParam(value = "Product id", example = "1", required = true) Long productId);

    @ApiOperation(value = "Find a photo of a restaurant’s product", hidden = true)
    ResponseEntity<?> findPhoto(Long restaurantId,
                                Long productId,
                                String acceptHeader)
            throws HttpMediaTypeNotAcceptableException;

    @ApiOperation("Update a restaurant's product photo")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product photo updated"),
            @ApiResponse(code = 404, message = "Restaurant product not found", response = ApiError.class)
    })
    ProductPhotoModel updatePhoto(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId,
                                  @ApiParam(value = "Product id", example = "1", required = true) Long productId,
                                  @ApiParam(name = "Request body", value = "Representation of a new product photo", required = true) ProductPhotoInput productPhotoInput,
                                  @ApiParam(value = "Product photo file (maximum 500KB, JPG and PNG only)", required = true) MultipartFile file)
            throws IOException;

    @ApiOperation("Deletes a restaurant's product photo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Product photo deleted"),
            @ApiResponse(code = 400, message = "Invalid restaurant or product id", response = ApiError.class),
            @ApiResponse(code = 404, message = "Product photo not found", response = ApiError.class)
    })
    void delete(@ApiParam(value = "Restaurant id", example = "1", required = true) Long restaurantId,
                @ApiParam(value = "Product id", example = "1", required = true) Long productId);

}
