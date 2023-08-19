package com.studies.foodorders.infrastructure.repositories.sells;

import com.studies.foodorders.domain.filter.DailySellsFilter;
import com.studies.foodorders.domain.models.order.Order;
import com.studies.foodorders.domain.models.order.dto.DailySells;
import com.studies.foodorders.domain.models.order.enumerations.OrderStatus;
import com.studies.foodorders.domain.repositories.sells.SellsRepositoryQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SellsRepositoryImpl implements SellsRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<DailySells> searchDailySales(DailySellsFilter filter) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(DailySells.class);
		var root = query.from(Order.class);
		var predicates = new ArrayList<Predicate>();

		//var functionDateCreationDate = builder.function("cast", Date.class, root.get("createdAt"), builder.literal("date"));

		var selection = builder.construct(DailySells.class,
				root.get("createdAt"),
				builder.count(root.get("id")),
				builder.sum(root.get("totalPrice")));

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

		predicates.add(root.get("status").in(
				OrderStatus.CONFIRMED, OrderStatus.DELIVERED));

		query.select(selection);
		query.where(predicates.toArray(new Predicate[0]));
		query.groupBy(root.get("createdAt"));

		return manager.createQuery(query).getResultList();
	}
}
