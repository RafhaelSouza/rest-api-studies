package com.studies.foodorders.api.model.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductPhotoModel {

	private String fileName;
	private String description;
	private String contentType;
	private Long fileSize;
	
}
