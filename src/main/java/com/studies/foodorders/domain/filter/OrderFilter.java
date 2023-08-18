package com.studies.foodorders.domain.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.OffsetDateTime;

@Setter
@Getter
public class OrderFilter {

	private Long clientId;
	private Long restaurantId;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime creationDateStart;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime creationDateEnd;
	
}
