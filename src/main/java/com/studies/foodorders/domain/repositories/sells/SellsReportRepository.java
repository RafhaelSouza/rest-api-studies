package com.studies.foodorders.domain.repositories.sells;

import com.studies.foodorders.domain.filter.DailySellsFilter;

public interface SellsReportRepository {

    byte[] searchDailySales(DailySellsFilter filter);

}
