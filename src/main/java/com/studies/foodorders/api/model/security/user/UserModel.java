package com.studies.foodorders.api.model.security.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "users")
@Setter
@Getter
public class UserModel extends RepresentationModel<UserModel> {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "User One")
	private String name;

	@ApiModelProperty(example = "email@domain.com")
	private String email;
	
}