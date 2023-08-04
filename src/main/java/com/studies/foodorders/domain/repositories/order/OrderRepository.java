package com.studies.foodorders.domain.repositories.order;

import com.studies.foodorders.domain.models.order.Order;
import com.studies.foodorders.domain.repositories.springcustom.CustomJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CustomJpaRepository<Order, Long> {
}
