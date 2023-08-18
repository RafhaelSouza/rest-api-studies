package com.studies.foodorders.domain.repositories.sells;

import com.studies.foodorders.domain.filter.DailySellsFilter;
import com.studies.foodorders.domain.models.order.dto.DailySells;

import java.util.List;

public interface SellsRepositoryQueries {

	List<DailySells> searchDailySales(DailySellsFilter filter);
	
}
