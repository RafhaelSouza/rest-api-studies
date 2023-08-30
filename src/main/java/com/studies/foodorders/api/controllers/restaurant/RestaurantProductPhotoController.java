package com.studies.foodorders.api.controllers.restaurant;

import com.studies.foodorders.api.converter.product.ProductPhotoModelConverter;
import com.studies.foodorders.api.model.product.ProductPhotoInput;
import com.studies.foodorders.api.model.product.ProductPhotoModel;
import com.studies.foodorders.domain.exceptions.NotFoundEntityException;
import com.studies.foodorders.domain.models.product.Product;
import com.studies.foodorders.domain.models.product.ProductPhoto;
import com.studies.foodorders.domain.repositories.product.ProductPhotoStorageService;
import com.studies.foodorders.domain.services.product.ProductPhotoService;
import com.studies.foodorders.domain.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPhotoService productPhotoService;

    @Autowired
    private ProductPhotoStorageService photoStorageService;

    @Autowired
    private ProductPhotoModelConverter converter;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductPhotoModel find(@PathVariable Long restaurantId,
                                   @PathVariable Long productId) {
        ProductPhoto productPhoto = productPhotoService.findIfExists(restaurantId, productId);

        return converter.toModel(productPhoto);
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> findImage(@PathVariable Long restaurantId,
                                                         @PathVariable Long productId) {
        try {
            ProductPhoto productPhoto = productPhotoService.findIfExists(restaurantId, productId);

            InputStream inputStream = photoStorageService.recover(productPhoto.getFileName());

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(inputStream));

        } catch (NotFoundEntityException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductPhotoModel updatePhoto(@PathVariable Long restaurantId,
                                         @PathVariable Long productId,
                                         @Valid ProductPhotoInput productPhotoInput) throws IOException {

        Product product = productService.findIfExists(restaurantId, productId);

        MultipartFile file = productPhotoInput.getFile();

        ProductPhoto productPhoto = new ProductPhoto();
        productPhoto.setProduct(product);
        productPhoto.setDescription(productPhotoInput.getDescription());
        productPhoto.setContentType(file.getContentType());
        productPhoto.setFileSize(file.getSize());
        productPhoto.setFileName(file.getOriginalFilename());

        ProductPhoto savedProductPhoto = productPhotoService.save(productPhoto, file.getInputStream());

        return converter.toModel(savedProductPhoto);

    }

}
