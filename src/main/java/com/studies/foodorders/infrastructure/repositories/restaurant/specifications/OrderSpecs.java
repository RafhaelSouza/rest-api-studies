package com.studies.foodorders.infrastructure.repositories.restaurant.specifications;

import com.studies.foodorders.domain.models.order.Order;
import com.studies.foodorders.domain.repositories.order.filter.OrderFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class OrderSpecs {

	public static Specification<Order> usingFilter(OrderFilter filter) {
		return (root, query, builder) -> {

			// Spring JPA perform a select count when it's used Page class, so it returns a number type, this condition is to escape these cases
			if (Order.class.equals(query.getResultType())) {
				root.fetch("restaurant").fetch("kitchen");
				root.fetch("client");
			}
			
			var predicates = new ArrayList<Predicate>();
			
			if (filter.getClientId() != null) {
				predicates.add(builder.equal(root.get("client"), filter.getClientId()));
			}
			
			if (filter.getRestaurantId() != null) {
				predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
			}
			
			if (filter.getCreationDateStart() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"),
						filter.getCreationDateStart()));
			}
			
			if (filter.getCreationDateEnd() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("createdAt"),
						filter.getCreationDateEnd()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}
