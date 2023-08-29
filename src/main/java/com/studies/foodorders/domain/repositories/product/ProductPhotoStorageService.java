package com.studies.foodorders.domain.repositories.product;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface ProductPhotoStorageService {

	default void replace(String oldFileName, NewProductPhoto newProductPhoto) {
		this.storage(newProductPhoto);

		if (oldFileName != null)
			this.delete(oldFileName);
	}

	default String fileNameGenerate() {
		return UUID.randomUUID().toString();
	}

	void storage(NewProductPhoto newProductPhoto);

	InputStream recover(String fileName);

	void delete(String fileName);
	
	@Builder
	@Getter
	class NewProductPhoto {
		
		private String fileName;
		private InputStream inputStream;
		
	}
	
}