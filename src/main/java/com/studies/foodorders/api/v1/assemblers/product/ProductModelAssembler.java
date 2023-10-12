package com.studies.foodorders.api.v1.assemblers.product;

import com.studies.foodorders.api.v1.controllers.restaurant.RestaurantProductController;
import com.studies.foodorders.api.v1.links.ProductLinks;
import com.studies.foodorders.api.v1.models.product.ProductInput;
import com.studies.foodorders.api.v1.models.product.ProductModel;
import com.studies.foodorders.domain.models.product.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductLinks productLinks;

    public ProductModelAssembler() {
        super(RestaurantProductController.class, ProductModel.class);
    }

    public ProductModel toModel(Product product) {

        ProductModel productModel = createModelWithId(product.getId(), product, product.getRestaurant().getId());

        modelMapper.map(product, productModel);

        productModel.add(productLinks.linkToProducts(product.getRestaurant().getId(), "products"));
        productModel.add(productLinks.linkToProductPhoto(product.getRestaurant().getId(),
                product.getId(), "photo"));

        return productModel;
    }

    public Product toDomainObject(ProductInput productInput) {
        return modelMapper.map(productInput, Product.class);
    }

    public void copyToDomainObject(ProductInput produtoInput, Product product) {
        modelMapper.map(produtoInput, product);
    }

}
