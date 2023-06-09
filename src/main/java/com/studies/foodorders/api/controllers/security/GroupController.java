package com.studies.foodorders.api.controllers.security;

import com.studies.foodorders.api.converter.security.GroupModelConverter;
import com.studies.foodorders.api.model.security.group.GroupInput;
import com.studies.foodorders.api.model.security.group.GroupModel;
import com.studies.foodorders.domain.models.security.Group;
import com.studies.foodorders.domain.services.security.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	@Autowired
	private GroupModelConverter groupModelConverter;
	
	@GetMapping
	public List<GroupModel> list() {
		List<Group> allGroups = groupService.list();
		
		return groupModelConverter.toCollectionModel(allGroups);
	}
	
	@GetMapping("/{id}")
	public GroupModel find(@PathVariable Long id) {
		Group group = groupService.findIfExists(id);
		
		return groupModelConverter.toModel(group);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GroupModel save(@RequestBody @Valid GroupInput groupInput) {
		Group group = groupModelConverter.toDomainObject(groupInput);
		
		group = groupService.save(group);
		
		return groupModelConverter.toModel(group);
	}
	
	@PutMapping("/{id}")
	public GroupModel update(@PathVariable Long id,
			@RequestBody @Valid GroupInput groupInput) {
		Group currentGroup = groupService.findIfExists(id);

		groupModelConverter.copyToDomainObject(groupInput, currentGroup);
		
		currentGroup = groupService.save(currentGroup);
		
		return groupModelConverter.toModel(currentGroup);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		groupService.delete(id);
	}
	
}
