package com.studies.foodorders.api.converter.product;

import com.studies.foodorders.api.model.product.ProductPhotoModel;
import com.studies.foodorders.domain.models.product.ProductPhoto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductPhotoModelConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public ProductPhotoModel toModel(ProductPhoto productPhoto) {
		return modelMapper.map(productPhoto, ProductPhotoModel.class);
	}
	
}
