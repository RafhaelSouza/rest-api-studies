package com.studies.foodorders.domain.models.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Setter
@Getter
public class DailySells {

    private OffsetDateTime sellDate;
    private Long totalSells;
    private BigDecimal totalBilled;

}
