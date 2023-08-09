package com.studies.foodorders.domain.repositories.order;

import com.studies.foodorders.domain.models.order.Order;
import com.studies.foodorders.domain.repositories.springcustom.CustomJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends CustomJpaRepository<Order, Long> {

    Optional<Order> findByCode(UUID code);

    @Query("from Order o join fetch o.client join fetch o.restaurant r join fetch r.kitchen")
    List<Order> findAll();

}
