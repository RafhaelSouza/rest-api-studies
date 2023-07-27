package com.studies.foodorders.api.converter.product;

import com.studies.foodorders.api.model.product.ProductInput;
import com.studies.foodorders.api.model.product.ProductModel;
import com.studies.foodorders.domain.models.product.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductModelConverter {

    @Autowired
    private ModelMapper modelMapper;

    public ProductModel toModel(Product product) {
        return modelMapper.map(product, ProductModel.class);
    }

    public List<ProductModel> toCollectionModel(List<Product> products) {
        return products.stream()
                .map(product -> toModel(product))
                .collect(Collectors.toList());
    }

    public Product toDomainObject(ProductInput productInput) {
        return modelMapper.map(productInput, Product.class);
    }

    public void copyToDomainObject(ProductInput produtoInput, Product product) {
        modelMapper.map(produtoInput, product);
    }

}
