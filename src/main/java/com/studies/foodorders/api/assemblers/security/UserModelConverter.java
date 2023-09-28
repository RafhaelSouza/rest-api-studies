package com.studies.foodorders.api.assemblers.security;

import com.studies.foodorders.api.model.security.user.UserInput;
import com.studies.foodorders.api.model.security.user.UserModel;
import com.studies.foodorders.domain.models.security.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserModelConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public UserModel toModel(User user) {
		return modelMapper.map(user, UserModel.class);
	}
	
	public List<UserModel> toCollectionModel(Collection<User> users) {
		return users.stream()
				.map(user -> toModel(user))
				.collect(Collectors.toList());
	}

	public User toDomainObject(UserInput userInput) {
		return modelMapper.map(userInput, User.class);
	}

	public void copyToDomainObject(UserInput userInput, User user) {
		modelMapper.map(userInput, user);
	}
	
}