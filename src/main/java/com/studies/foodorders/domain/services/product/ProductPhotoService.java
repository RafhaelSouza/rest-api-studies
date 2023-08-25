package com.studies.foodorders.domain.services.product;

import com.studies.foodorders.domain.models.product.ProductPhoto;
import com.studies.foodorders.domain.repositories.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductPhotoService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductPhoto salvar(ProductPhoto productPhoto) {
        return productRepository.save(productPhoto);
    }

}
