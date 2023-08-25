package com.studies.foodorders.api.controllers.restaurant;

import com.studies.foodorders.api.model.product.ProductPhotoInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updatePhoto(@Valid ProductPhotoInput productPhotoInput) {

        var fileName = UUID.randomUUID()
                + "_" + productPhotoInput.getFile().getOriginalFilename();

        var filePhoto = Path.of("/home/users/catalog/", fileName);

        System.out.println(productPhotoInput.getDescription());
        System.out.println(filePhoto);
        System.out.println(productPhotoInput.getFile().getContentType());

        try {
            productPhotoInput.getFile().transferTo(filePhoto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
