package com.studies.foodorders.api.model.product;

import com.studies.foodorders.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PhotoProductInput {

	@NotNull
	@FileSize(max = "300KB")
	private MultipartFile file;

	@NotBlank
	private String description;
	
}