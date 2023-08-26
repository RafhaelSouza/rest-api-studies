package com.studies.foodorders.domain.repositories.product;

import com.studies.foodorders.domain.models.product.Product;
import com.studies.foodorders.domain.models.product.ProductPhoto;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQueries {

    @Query("from Product where restaurant.id = :restaurant and id = :product")
    Optional<Product> findById(@Param("restaurant") Long restaurantId,
                               @Param("product") Long productId);

    List<Product> findAllByRestaurant(Restaurant restaurant);

    @Query("from Product p where p.active = true and p.restaurant = :restaurant")
    List<Product> findActivesByRestaurant(Restaurant restaurant);

    @Query("select pf from ProductPhoto pf join pf.product p "
            + "where p.restaurant.id = :restaurantId and pf.product.id = :productId")
    Optional<ProductPhoto> findPhotoById(Long restaurantId, Long productId);

}
