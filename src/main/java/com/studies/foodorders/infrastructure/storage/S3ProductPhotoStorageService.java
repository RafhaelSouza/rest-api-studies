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
public class S3ProductPhotoStorageService implements ProductPhotoStorageService {

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public void storage(NewProductPhoto newProductPhoto) {

    }

    @Override
    public InputStream recover(String fileName) {

        return null;
    }

    @Override
    public void delete(String fileName) {

    }

    private Path getFilePath(String fileName) {
        return null;
    }
}
