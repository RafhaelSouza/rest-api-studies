package com.studies.foodorders.api.openapi.models;

import com.studies.foodorders.api.model.security.group.GroupModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("GroupsModel")
@Data
public class GroupsModelOpenApi {

	private GroupsEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("GroupsEmbeddedModel")
	@Data
	public class GroupsEmbeddedModelOpenApi {

		private List<GroupModel> groups;

	}

}
