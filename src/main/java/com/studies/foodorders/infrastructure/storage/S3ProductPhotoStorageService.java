package com.studies.foodorders.infrastructure.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.studies.foodorders.core.storage.StorageProperties;
import com.studies.foodorders.domain.repositories.product.ProductPhotoStorageService;
import com.studies.foodorders.infrastructure.exceptions.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class S3ProductPhotoStorageService implements ProductPhotoStorageService {

    @Autowired
    private StorageProperties storageProperties;

    @Autowired
    private AmazonS3 amazonS3;

    @Override
    public void storage(NewProductPhoto newProductPhoto) {
        try {
            String filePath = getFilePath(newProductPhoto.getFileName());

            var objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(newProductPhoto.getContentType());

            var putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    filePath,
                    newProductPhoto.getInputStream(),
                    objectMetadata);
                    //.withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Unable to send file to Amazon S3", e);
        }
    }

    @Override
    public RecoveredPhoto recover(String fileName) {

        String filePath = getFilePath(fileName);

        URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), filePath);

        return RecoveredPhoto.builder()
                .url(url.toString()).build();
    }

    @Override
    public void delete(String fileName) {
        try {
            String filePath = getFilePath(fileName);

            var deleteObjectRequest = new DeleteObjectRequest(
                    storageProperties.getS3().getBucket(), filePath);

            amazonS3.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Unable to delete file in Amazon S3", e);
        }
    }

    private String getFilePath(String fileName) {
        return String.format("%s/%s", storageProperties.getS3().getPhotosDirectory(), fileName);
    }
}
