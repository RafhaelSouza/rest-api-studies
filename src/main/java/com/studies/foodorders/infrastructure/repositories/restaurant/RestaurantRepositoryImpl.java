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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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

        var jpql = new StringBuilder();
        jpql.append("from Restaurant where 1 = 1 ");

        var parameters = new HashMap<String, Object>();

        if (StringUtils.hasLength(name)) {
            jpql.append("and name like :name ");
            parameters.put("name", "%" + name + "%");
        }

        if (initialCost != null) {
            jpql.append("shippingCosts >= :initialCost ");
            parameters.put("initialCost", initialCost);
        }

        if (finalCost != null) {
            jpql.append("shippingCosts <= :finalCost ");
            parameters.put("finalCost", finalCost);
        }

        TypedQuery<Restaurant> query = entityManager.createQuery(jpql.toString(), Restaurant.class);

        parameters.forEach((key, value) -> query.setParameter(key, value));

        //return query.getResultList();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteria.from(Restaurant.class);

        List<Predicate> conditionals = new ArrayList<>();

        if (StringUtils.hasLength(name)) {
            conditionals.add(builder.like(root.get("name"), "%" + name + "%"));
        }
        if (initialCost != null) {
            conditionals.add(builder.greaterThanOrEqualTo(root.get("shippingCosts"), initialCost));
        }
        if (finalCost != null) {
            conditionals.add(builder.lessThanOrEqualTo(root.get("shippingCosts"), finalCost));
        }

        criteria.where(conditionals.toArray(new Predicate[0]));

        TypedQuery<Restaurant> queryCriteria = entityManager.createQuery(criteria);

        //return queryCriteria.getResultList();

        return null;

    }

    @Override
    public List<Restaurant> findWithFreeShipping(String name) {
        return restaurantRepository.findAll(withFreeShipping()
                .and(withSimilarName(name)));
    }

}
