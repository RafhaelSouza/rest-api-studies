package com.studies.foodorders.domain.services.product;

import com.studies.foodorders.domain.models.product.ProductPhoto;
import com.studies.foodorders.domain.repositories.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductPhotoService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductPhoto save(ProductPhoto productPhoto) {

        Long restaurantId = productPhoto.getRestaurantId();
        Long productId = productPhoto.getProduct().getId();

        Optional<ProductPhoto> existentPhoto = productRepository
                .findPhotoById(restaurantId, productId);

        if (existentPhoto.isPresent()) {
            productRepository.delete(existentPhoto.get());
        }

        return productRepository.save(productPhoto);
    }

}
