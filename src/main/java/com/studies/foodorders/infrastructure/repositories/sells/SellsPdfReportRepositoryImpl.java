package com.studies.foodorders.infrastructure.repositories.sells;

import com.studies.foodorders.domain.filter.DailySellsFilter;
import com.studies.foodorders.domain.repositories.sells.SellsReportRepository;
import com.studies.foodorders.domain.repositories.sells.SellsRepositoryQueries;
import com.studies.foodorders.infrastructure.exceptions.ReportException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Locale;

@Repository
public class SellsPdfReportRepositoryImpl implements SellsReportRepository {

	@Autowired
	private SellsRepositoryQueries sellsRepositoryQueries;

	@Override
	public byte[] searchDailySales(DailySellsFilter filter) {
		try {
			var inputStream = this.getClass().getResourceAsStream(
					"/reports/daily-sells.jasper");

			var parameters = new HashMap<String, Object>();
			parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));

			var dailySells = sellsRepositoryQueries.searchDailySales(filter);
			var dataSource = new JRBeanCollectionDataSource(dailySells);

			var jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			throw new ReportException("Unable to generate daily sales report", e);
		}
	}
}
