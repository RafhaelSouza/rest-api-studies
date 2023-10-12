package com.studies.foodorders.api.v1.openapi.models;

import com.studies.foodorders.api.v1.models.localization.state.StateModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("StatesModel")
@Data
public class StatesModelOpenApi {

	private StatesEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("StatesEmbeddedModel")
	@Data
	public class StatesEmbeddedModelOpenApi {

		private List<StateModel> states;

	}

}
