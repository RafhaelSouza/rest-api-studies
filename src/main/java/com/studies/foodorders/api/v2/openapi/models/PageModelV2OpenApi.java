package com.studies.foodorders.api.v2.openapi.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageModelV2OpenApi {

	@ApiModelProperty(example = "10", value = "Number of records per page")
	private Long size;

	@ApiModelProperty(example = "50", value = "Total records")
	private Long totalElements;

	@ApiModelProperty(example = "5", value = "Total pages")
	private Long totalPages;

	@ApiModelProperty(example = "0", value = "Page number (starts from 0)")
	private Long number;

}
