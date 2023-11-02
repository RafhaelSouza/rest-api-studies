package com.studies.foodorders.api.v1.controllers.sells;

import com.studies.foodorders.api.v1.links.StatisticLinks;
import com.studies.foodorders.api.v1.openapi.controllers.StatisticsControllerOpenApi;
import com.studies.foodorders.core.security.CheckSecurity;
import com.studies.foodorders.domain.filter.DailySellsFilter;
import com.studies.foodorders.domain.models.order.dto.DailySells;
import com.studies.foodorders.domain.repositories.sells.SellsReportRepository;
import com.studies.foodorders.domain.repositories.sells.SellsRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/statistics")
public class StatisticsController implements StatisticsControllerOpenApi {

	@Autowired
	private SellsRepositoryQueries sellsRepositoryQueries;

	@Autowired
	private SellsReportRepository sellsReportRepository;

	@Autowired
	private StatisticLinks statisticLinks;

	@CheckSecurity.Statistics.AllowToSearch
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public StatisticsModel statistics() {
		var statisticsModel = new StatisticsModel();

		statisticsModel.add(statisticLinks.linkToStatisticsSailySells("daily-sells"));

		return statisticsModel;
	}

	@CheckSecurity.Statistics.AllowToSearch
	@GetMapping(path = "/daily-sells", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DailySells> searchDailySales(DailySellsFilter filter) {
		return sellsRepositoryQueries.searchDailySales(filter);
	}

	@CheckSecurity.Statistics.AllowToSearch
	@GetMapping(path = "/daily-sells", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> searchDailySalesPdf(DailySellsFilter filter) {
		byte[] bytesPdf = sellsReportRepository.searchDailySales(filter);

		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=daily-sells.pdf");

		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.headers(headers)
				.body(bytesPdf);
	}

	public static class StatisticsModel extends RepresentationModel<StatisticsModel> {}
	
}
