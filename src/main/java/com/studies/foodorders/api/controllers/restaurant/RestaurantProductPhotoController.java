package com.studies.foodorders.api.controllers.restaurant;

import com.studies.foodorders.api.model.product.PhotoProductInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updatePhoto(PhotoProductInput photoProductInput) {

        var fileName = UUID.randomUUID()
                + "_" + photoProductInput.getFile().getOriginalFilename();

        var filePhoto = Path.of("/home/user/catalog/", fileName);

        System.out.println(photoProductInput.getDescription());
        System.out.println(filePhoto);
        System.out.println(photoProductInput.getFile().getContentType());

        try {
            photoProductInput.getFile().transferTo(filePhoto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
