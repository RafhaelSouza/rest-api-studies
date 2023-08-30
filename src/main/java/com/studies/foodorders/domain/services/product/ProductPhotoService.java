package com.studies.foodorders.domain.services.product;

import com.studies.foodorders.domain.exceptions.ProductPhotoNotFoundException;
import com.studies.foodorders.domain.models.product.ProductPhoto;
import com.studies.foodorders.domain.repositories.product.ProductPhotoStorageService;
import com.studies.foodorders.domain.repositories.product.ProductPhotoStorageService.NewProductPhoto;
import com.studies.foodorders.domain.repositories.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class ProductPhotoService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductPhotoStorageService productPhotoStorageService;

    @Transactional
    public ProductPhoto save(ProductPhoto productPhoto, InputStream fileData) {

        Long restaurantId = productPhoto.getRestaurantId();
        Long productId = productPhoto.getProduct().getId();
        String newFileName = productPhotoStorageService.fileNameGenerate();
        String currentFileName = null;

        Optional<ProductPhoto> existentPhoto = productRepository
                .findPhotoById(restaurantId, productId);

        if (existentPhoto.isPresent()) {
            currentFileName = existentPhoto.get().getFileName();
            productRepository.delete(existentPhoto.get());
        }

        productPhoto.setFileName(newFileName);
        productPhoto = productRepository.save(productPhoto);
        productRepository.flush();

        NewProductPhoto newProductPhoto = NewProductPhoto
                .builder()
                .fileName(productPhoto.getFileName())
                .inputStream(fileData)
                .build();

        productPhotoStorageService.replace(currentFileName, newProductPhoto);

        return productPhoto;
    }

    @Transactional
    public void delete(Long restaurantId, Long productId) {
        ProductPhoto productPhoto = findIfExists(restaurantId, productId);

        productRepository.delete(productPhoto);
        productRepository.flush();

        productPhotoStorageService.delete(productPhoto.getFileName());
    }

    public ProductPhoto findIfExists(Long restaurantId, Long productId) {
        return productRepository.findPhotoById(restaurantId, productId)
                .orElseThrow(() -> new ProductPhotoNotFoundException(restaurantId, productId));
    }

}
