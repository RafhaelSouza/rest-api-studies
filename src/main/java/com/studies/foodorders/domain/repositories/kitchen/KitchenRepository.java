package com.studies.foodorders.domain.repositories.kitchen;

import com.studies.foodorders.domain.models.kitchen.Kitchen;
import com.studies.foodorders.domain.repositories.springcustom.CustomJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KitchenRepository extends CustomJpaRepository<Kitchen, Long> {

    List<Kitchen> findAllByNameContaining(String name);
    Optional<Kitchen> findByName(String name);
    boolean existsByName(String name);

}
