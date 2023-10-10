package com.studies.foodorders.api.v1.controllers.restaurant;

import com.studies.foodorders.api.v1.assemblers.product.ProductModelAssembler;
import com.studies.foodorders.api.v1.links.ProductLinks;
import com.studies.foodorders.api.v1.models.product.ProductInput;
import com.studies.foodorders.api.v1.models.product.ProductModel;
import com.studies.foodorders.api.v1.openapi.controllers.RestaurantProductControllerOpenApi;
import com.studies.foodorders.domain.models.product.Product;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.repositories.product.ProductRepository;
import com.studies.foodorders.domain.services.product.ProductService;
import com.studies.foodorders.domain.services.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products")
public class RestaurantProductController implements RestaurantProductControllerOpenApi {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ProductModelAssembler productModelAssembler;

    @Autowired
    private ProductLinks productLinks;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<ProductModel> list(@PathVariable Long restaurantId,
                                              @RequestParam(required = false) Boolean includeInactives) {
        Restaurant restaurant = restaurantService.findIfExists(restaurantId);

        List<Product> allProducts;

        if (includeInactives) {
            allProducts = productRepository.findAllByRestaurant(restaurant);
        } else {
            allProducts = productRepository.findActivesByRestaurant(restaurant);
        }

        return productModelAssembler.toCollectionModel(allProducts)
                .add(productLinks.linkToProducts(restaurantId));
    }

    @GetMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductModel find(@PathVariable Long restaurantId, @PathVariable Long productId) {
        Product product = productService.findIfExists(restaurantId, productId);

        return productModelAssembler.toModel(product);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductModel save(@PathVariable Long restaurantId,
                                  @RequestBody @Valid ProductInput productInput) {
        Restaurant restaurant = restaurantService.findIfExists(restaurantId);

        Product product = productModelAssembler.toDomainObject(productInput);
        product.setRestaurant(restaurant);

        product = productService.save(product);

        return productModelAssembler.toModel(product);
    }

    @PutMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductModel update(@PathVariable Long restaurantId, @PathVariable Long productId,
                                  @RequestBody @Valid ProductInput productInput) {
        Product currentProduct = productService.findIfExists(restaurantId, productId);

        productModelAssembler.copyToDomainObject(productInput, currentProduct);

        currentProduct = productService.save(currentProduct);

        return productModelAssembler.toModel(currentProduct);
    }

}
