package com.studies.foodorders.domain.repositories.restaurant;

import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.repositories.springcustom.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository
        extends CustomJpaRepository<Restaurant, Long>,
        JpaSpecificationExecutor<Restaurant>,
        RestaurantRepositoryQueries {

    List<Restaurant> findByshippingCostsBetween(BigDecimal initialCost, BigDecimal finalCost);
    List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchenId);
    List<Restaurant> findTop4ByNameContaining(String name);
    Optional<Restaurant> findFirstRestaurantByNameContaining(String name);
    int countByKitchenId(Long kitchenId);
    List<Restaurant> searchByName(String name, Long kitchenId);
    public List<Restaurant> find(String name, BigDecimal initialCost, BigDecimal finalCost);
    @Query("from Restaurant r join r.kitchen left outer join fetch r.paymentWay")
    List<Restaurant> findAll();

}
