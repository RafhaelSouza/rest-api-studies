package com.studies.foodorders.api.assemblers.security;

import com.studies.foodorders.api.controllers.security.UserController;
import com.studies.foodorders.api.controllers.security.UserGroupController;
import com.studies.foodorders.api.model.security.user.UserInput;
import com.studies.foodorders.api.model.security.user.UserModel;
import com.studies.foodorders.domain.models.security.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

	@Autowired
	private ModelMapper modelMapper;

	public UserModelAssembler() {
		super(UserController.class, UserModel.class);
	}
	
	public UserModel toModel(User user) {

		UserModel userModel = createModelWithId(user.getId(), user);

		modelMapper.map(user, userModel);

		userModel.add(linkTo(UserController.class).withRel("users"));

		userModel.add(linkTo(methodOn(UserGroupController.class)
				.list(user.getId())).withRel("user-group"));

		return userModel;
	}

	@Override
	public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> entities) {
		return super.toCollectionModel(entities)
				.add(linkTo(UserController.class).withSelfRel());
	}

	public User toDomainObject(UserInput userInput) {
		return modelMapper.map(userInput, User.class);
	}

	public void copyToDomainObject(UserInput userInput, User user) {
		modelMapper.map(userInput, user);
	}
	
}