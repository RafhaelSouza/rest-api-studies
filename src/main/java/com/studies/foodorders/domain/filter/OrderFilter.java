package com.studies.foodorders.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.OffsetDateTime;

@Setter
@Getter
public class OrderFilter {

	@ApiModelProperty(example = "1", value = "Customer id for search filter")
	private Long clientId;

	@ApiModelProperty(example = "1", value = "Restaurant id for search filter")
	private Long restaurantId;

	@ApiModelProperty(example = "2023-09-20T09:00:00Z",
			value = "Initial creation date/time for search filter")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime creationDateStart;

	@ApiModelProperty(example = "2023-09-20T09:00:00Z",
			value = "End creation date/time for search filter")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime creationDateEnd;
	
}
