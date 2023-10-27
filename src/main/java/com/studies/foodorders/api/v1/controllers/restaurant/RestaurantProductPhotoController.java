package com.studies.foodorders.api.v1.controllers.restaurant;

import com.studies.foodorders.api.v1.assemblers.product.ProductPhotoModelAssembler;
import com.studies.foodorders.api.v1.models.product.ProductPhotoInput;
import com.studies.foodorders.api.v1.models.product.ProductPhotoModel;
import com.studies.foodorders.api.v1.openapi.controllers.RestaurantProductPhotoControllerOpenApi;
import com.studies.foodorders.core.security.CheckSecurity;
import com.studies.foodorders.domain.exceptions.NotFoundEntityException;
import com.studies.foodorders.domain.models.product.Product;
import com.studies.foodorders.domain.models.product.ProductPhoto;
import com.studies.foodorders.domain.repositories.product.ProductPhotoStorageService;
import com.studies.foodorders.domain.repositories.product.ProductPhotoStorageService.RecoveredPhoto;
import com.studies.foodorders.domain.services.product.ProductPhotoService;
import com.studies.foodorders.domain.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController implements RestaurantProductPhotoControllerOpenApi {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPhotoService productPhotoService;

    @Autowired
    private ProductPhotoStorageService photoStorageService;

    @Autowired
    private ProductPhotoModelAssembler productPhotoModelAssembler;

    @CheckSecurity.Restaurants.AllowSearch
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductPhotoModel find(@PathVariable Long restaurantId,
                                   @PathVariable Long productId) {
        ProductPhoto productPhoto = productPhotoService.findIfExists(restaurantId, productId);

        return productPhotoModelAssembler.toModel(productPhoto);
    }

    @CheckSecurity.Restaurants.AllowSearch
    @GetMapping(produces = MediaType.ALL_VALUE)
    public ResponseEntity<?> findPhoto(@PathVariable Long restaurantId,
                                                         @PathVariable Long productId,
                                                         @RequestHeader(name = "accept") String acceptHeader)
                                                         throws HttpMediaTypeNotAcceptableException {
        try {
            ProductPhoto productPhoto = productPhotoService.findIfExists(restaurantId, productId);

            MediaType photoMediaType = MediaType.parseMediaType(productPhoto.getContentType());
            List<MediaType> acceptableMediaTypes = MediaType.parseMediaTypes(acceptHeader);

            checkMediaTypeCompatibility(photoMediaType, acceptableMediaTypes);

            RecoveredPhoto recoveredPhoto = photoStorageService.recover(productPhoto.getFileName());

            if (recoveredPhoto.isThereUrl())
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, recoveredPhoto.getUrl())
                        .build();

            return ResponseEntity.ok()
                    .contentType(photoMediaType)
                    .body(new InputStreamResource(recoveredPhoto.getInputStream()));

        } catch (NotFoundEntityException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @CheckSecurity.Restaurants.AllowUpdate
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductPhotoModel updatePhoto(@PathVariable Long restaurantId,
                                         @PathVariable Long productId,
                                         @Valid ProductPhotoInput productPhotoInput,
                                         @RequestPart MultipartFile file) throws IOException {

        Product product = productService.findIfExists(restaurantId, productId);

        //MultipartFile file = productPhotoInput.getFile();

        ProductPhoto productPhoto = new ProductPhoto();
        productPhoto.setProduct(product);
        productPhoto.setDescription(productPhotoInput.getDescription());
        productPhoto.setContentType(file.getContentType());
        productPhoto.setFileSize(file.getSize());
        productPhoto.setFileName(file.getOriginalFilename());

        ProductPhoto savedProductPhoto = productPhotoService.save(productPhoto, file.getInputStream());

        return productPhotoModelAssembler.toModel(savedProductPhoto);

    }

    @CheckSecurity.Restaurants.AllowUpdate
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restaurantId,
                        @PathVariable Long productId) {
        productPhotoService.delete(restaurantId, productId);
    }

    private void checkMediaTypeCompatibility(MediaType photoMediaType,
                                                   List<MediaType> acceptableMediaTypes)
            throws HttpMediaTypeNotAcceptableException {

        boolean compatible = acceptableMediaTypes.stream()
                .anyMatch(acceptableMediaType -> acceptableMediaType.isCompatibleWith(photoMediaType));

        if (!compatible)
            throw new HttpMediaTypeNotAcceptableException(acceptableMediaTypes);
    }

}
