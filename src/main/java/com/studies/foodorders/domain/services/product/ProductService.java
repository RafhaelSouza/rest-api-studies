package com.studies.foodorders.domain.services.product;

import com.studies.foodorders.domain.exceptions.ProductNotFoundException;
import com.studies.foodorders.domain.models.product.Product;
import com.studies.foodorders.domain.repositories.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findIfExists(Long restaurantId, Long productId) {
        return productRepository.findById(restaurantId, productId)
                .orElseThrow(() -> new ProductNotFoundException(restaurantId, productId));
    }

}
