package com.studies.foodorders.api.v1.assemblers.security;

import com.studies.foodorders.api.v1.controllers.security.UsersController;
import com.studies.foodorders.api.v1.links.UserLinks;
import com.studies.foodorders.api.v1.models.security.user.UserInput;
import com.studies.foodorders.api.v1.models.security.user.UserModel;
import com.studies.foodorders.domain.models.security.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<Users, UserModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserLinks userLinks;

	public UserModelAssembler() {
		super(UsersController.class, UserModel.class);
	}
	
	public UserModel toModel(Users users) {

		UserModel userModel = createModelWithId(users.getId(), users);

		modelMapper.map(users, userModel);

		userModel.add(userLinks.linkToUsers("users"));

		userModel.add(userLinks.linkToUserGroups(users.getId(),"user-group"));

		return userModel;
	}

	@Override
	public CollectionModel<UserModel> toCollectionModel(Iterable<? extends Users> entities) {
		return super.toCollectionModel(entities)
				.add(userLinks.linkToUsers());
	}

	public Users toDomainObject(UserInput userInput) {
		return modelMapper.map(userInput, Users.class);
	}

	public void copyToDomainObject(UserInput userInput, Users users) {
		modelMapper.map(userInput, users);
	}
	
}