package com.studies.foodorders.domain.repositories.product;

import java.io.InputStream;

import lombok.Builder;
import lombok.Getter;

public interface ProductPhotoStorageService {

	void storage(NewProductPhoto newProductPhoto);
	
	@Builder
	@Getter
	class NewProductPhoto {
		
		private String fileName;
		private InputStream inputStream;
		
	}
	
}