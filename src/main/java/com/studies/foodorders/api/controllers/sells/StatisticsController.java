package com.studies.foodorders.api.controllers.sells;

import com.studies.foodorders.domain.filter.DailySellsFilter;
import com.studies.foodorders.domain.models.order.dto.DailySells;
import com.studies.foodorders.domain.repositories.sells.SellsRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/statistics")
public class StatisticsController {

	@Autowired
	private SellsRepositoryQueries sellsRepositoryQueries;
	
	@GetMapping("/daily-sells")
	public List<DailySells> searchDailySales(DailySellsFilter filter) {
		return sellsRepositoryQueries.searchDailySales(filter);
	}
	
}
