package com.studies.foodorders.infrastructure.repositories.restaurant;

import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.repositories.restaurant.RestaurantRepository;
import com.studies.foodorders.domain.repositories.restaurant.RestaurantRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.studies.foodorders.infrastructure.repositories.restaurant.specifications.RestaurantSpecs.withFreeShipping;
import static com.studies.foodorders.infrastructure.repositories.restaurant.specifications.RestaurantSpecs.withSimilarName;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Lazy
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> find(String name, BigDecimal initialCost, BigDecimal finalCost) {

        var builder = entityManager.getCriteriaBuilder();

        var criteria = builder.createQuery(Restaurant.class);
        var root = criteria.from(Restaurant.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText(name)) {
            predicates.add(builder.like(root.get("name"), "%" + name + "%"));
        }

        if (initialCost != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("shippingCosts"), initialCost));
        }

        if (finalCost != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("shippingCosts"), finalCost));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        var query = entityManager.createQuery(criteria);

        return query.getResultList();

    }

    @Override
    public List<Restaurant> findWithFreeShipping(String name) {
        return restaurantRepository.findAll(withFreeShipping()
                .and(withSimilarName(name)));
    }

}
