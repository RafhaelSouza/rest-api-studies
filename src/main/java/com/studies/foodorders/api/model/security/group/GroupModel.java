package com.studies.foodorders.api.model.security.group;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "groups")
@Setter
@Getter
public class GroupModel extends RepresentationModel<GroupModel> {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Manager")
	private String name;
	
}
