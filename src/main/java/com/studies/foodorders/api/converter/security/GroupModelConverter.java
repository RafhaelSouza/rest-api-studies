package com.studies.foodorders.api.converter.security;

import com.studies.foodorders.api.model.security.GroupInput;
import com.studies.foodorders.api.model.security.GroupModel;
import com.studies.foodorders.domain.models.security.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupModelConverter {

    @Autowired
    private ModelMapper modelMapper;

    public GroupModel toModel(Group group) {
        return modelMapper.map(group, GroupModel.class);
    }

    public List<GroupModel> toCollectionModel(List<Group> groups) {
        return groups.stream()
                .map(group -> toModel(group))
                .collect(Collectors.toList());
    }

    public Group toDomainObject(GroupInput groupInput) {
        return modelMapper.map(groupInput, Group.class);
    }

    public void copyToDomainObject(GroupInput groupInput, Group group) {
        modelMapper.map(groupInput, group);
    }

}
