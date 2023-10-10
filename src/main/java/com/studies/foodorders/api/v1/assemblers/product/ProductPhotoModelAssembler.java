package com.studies.foodorders.api.v1.assemblers.product;

import com.studies.foodorders.api.v1.controllers.restaurant.RestaurantProductPhotoController;
import com.studies.foodorders.api.v1.links.ProductLinks;
import com.studies.foodorders.api.v1.models.product.ProductPhotoModel;
import com.studies.foodorders.domain.models.product.ProductPhoto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductPhotoModelAssembler extends RepresentationModelAssemblerSupport<ProductPhoto, ProductPhotoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductLinks productLinks;

	public ProductPhotoModelAssembler() {
		super(RestaurantProductPhotoController.class, ProductPhotoModel.class);
	}
	
	public ProductPhotoModel toModel(ProductPhoto productPhoto) {

		ProductPhotoModel productPhotoModel = modelMapper.map(productPhoto, ProductPhotoModel.class);

		productPhotoModel.add(productLinks.linkToProductPhoto(
				productPhoto.getRestaurantId(), productPhoto.getProduct().getId()));

		productPhotoModel.add(productLinks.linkToProduct(
				productPhoto.getRestaurantId(), productPhoto.getProduct().getId(), "product"));

		return modelMapper.map(productPhoto, ProductPhotoModel.class);
	}
	
}
