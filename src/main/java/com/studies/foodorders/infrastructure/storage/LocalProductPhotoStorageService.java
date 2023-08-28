package com.studies.foodorders.infrastructure.storage;

import com.studies.foodorders.domain.repositories.product.ProductPhotoStorageService;
import com.studies.foodorders.infrastructure.exceptions.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalProductPhotoStorageService implements ProductPhotoStorageService {

    @Value("${com.studies.storage.local.product-photos-directory}")
    private Path photoDirectory;
    @Override
    public void storage(NewProductPhoto newProductPhoto) {
        try {
            Path arquivoPath = getPathFile(newProductPhoto.getFileName());

            FileCopyUtils.copy(newProductPhoto.getInputStream(),
                    Files.newOutputStream(arquivoPath));
        } catch (Exception e) {
            throw new StorageException("Unable to store file", e);
        }
    }

    private Path getPathFile(String fileName) {
        return photoDirectory.resolve(Path.of(fileName));
    }
}
