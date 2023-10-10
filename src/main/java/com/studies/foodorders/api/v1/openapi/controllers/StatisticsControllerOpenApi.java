package com.studies.foodorders.api.v1.openapi.controllers;

import com.studies.foodorders.domain.filter.DailySellsFilter;
import com.studies.foodorders.domain.models.order.dto.DailySells;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.studies.foodorders.api.v1.controllers.sells.StatisticsController.StatisticsModel;

@Api(tags = "Statistics")
public interface StatisticsControllerOpenApi {

    @ApiOperation(value = "Statistics", hidden = true)
    StatisticsModel statistics();

    @ApiOperation("Check daily sales statistics")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "restaurantId", value = "Restaurante id",
                    example = "1", dataType = "int"),
            @ApiImplicitParam(name = "creationDateStart", value = "Order creation start date/time",
                    example = "2023-09-26T00:00:00Z", dataType = "date-time"),
            @ApiImplicitParam(name = "creationDateEnd", value = "Order creation end date/time",
                    example = "2023-09-26T23:59:59Z", dataType = "date-time")
    })
    List<DailySells> searchDailySales(DailySellsFilter filter);

    ResponseEntity<byte[]> searchDailySalesPdf(DailySellsFilter filter);

}
