package com.studies.foodorders.api.model.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductPhotoModel {

	@ApiModelProperty(example = "3b686e44-b6dc-4a9f-ab43-f8b872d8e2bd.jpg")
	private String fileName;

	@ApiModelProperty(example = "Pork with sweet and sour sauce")
	private String description;

	@ApiModelProperty(example = "image/jpeg")
	private String contentType;

	@ApiModelProperty(example = "403952")
	private Long fileSize;
	
}
