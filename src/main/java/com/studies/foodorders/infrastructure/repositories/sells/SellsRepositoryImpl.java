package com.studies.foodorders.infrastructure.repositories.sells;

import com.studies.foodorders.domain.filter.DailySellsFilter;
import com.studies.foodorders.domain.models.order.Order;
import com.studies.foodorders.domain.models.order.dto.DailySells;
import com.studies.foodorders.domain.repositories.sells.SellsRepositoryQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
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

		//var functionDateCreationDate = builder.function("cast", Date.class, root.get("createdAt"), builder.literal("date"));

		var selection = builder.construct(DailySells.class,
				root.get("createdAt"),
				builder.count(root.get("id")),
				builder.sum(root.get("totalPrice")));

		query.select(selection);
		query.groupBy(root.get("createdAt"));

		return manager.createQuery(query).getResultList();
	}
}
