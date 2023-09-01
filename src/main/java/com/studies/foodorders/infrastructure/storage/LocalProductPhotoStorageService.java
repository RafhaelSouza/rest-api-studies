package com.studies.foodorders.infrastructure.storage;

import com.studies.foodorders.core.storage.StorageProperties;
import com.studies.foodorders.domain.repositories.product.ProductPhotoStorageService;
import com.studies.foodorders.infrastructure.exceptions.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalProductPhotoStorageService implements ProductPhotoStorageService {

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public void storage(NewProductPhoto newProductPhoto) {
        try {
            Path filePath = getFilePath(newProductPhoto.getFileName());

            FileCopyUtils.copy(newProductPhoto.getInputStream(),
                    Files.newOutputStream(filePath));
        } catch (Exception e) {
            throw new StorageException("Unable to store file", e);
        }
    }

    @Override
    public InputStream recover(String fileName) {
        try {
            Path filePath = getFilePath(fileName);

            return Files.newInputStream(filePath);
        } catch (Exception e) {
            throw new StorageException("Unable to recover file", e);
        }
    }

    @Override
    public void delete(String fileName) {
        try {
            Path filePath = getFilePath(fileName);

            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new StorageException("Unable to delete file", e);
        }
    }

    private Path getFilePath(String fileName) {
        return storageProperties.getLocal()
                .getProductPhotosDirectory()
                .resolve(Path.of(fileName));
    }
}
