package com.studies.foodorders.domain.repositories.product;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface ProductPhotoStorageService {

	void storage(NewProductPhoto newProductPhoto);

	void delete(String fileName);

	default void replace(String oldFileName, NewProductPhoto newProductPhoto) {
		this.storage(newProductPhoto);

		if (oldFileName != null)
			this.delete(oldFileName);
	}

	default String fileNameGenerate() {
		return UUID.randomUUID().toString();
	}
	
	@Builder
	@Getter
	class NewProductPhoto {
		
		private String fileName;
		private InputStream inputStream;
		
	}
	
}