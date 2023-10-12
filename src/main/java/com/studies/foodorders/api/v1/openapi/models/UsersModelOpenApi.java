package com.studies.foodorders.api.v1.openapi.models;

import com.studies.foodorders.api.v1.models.security.user.UserModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("UsersModel")
@Data
public class UsersModelOpenApi {

	private UsersEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("UsersEmbeddedModel")
	@Data
	public class UsersEmbeddedModelOpenApi {

		private List<UserModel> users;

	}

}
