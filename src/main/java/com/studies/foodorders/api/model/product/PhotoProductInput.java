package com.studies.foodorders.api.model.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PhotoProductInput {

	private MultipartFile file;
	private String description;
	
}