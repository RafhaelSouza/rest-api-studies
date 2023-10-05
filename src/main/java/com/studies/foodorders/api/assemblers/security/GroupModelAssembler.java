package com.studies.foodorders.api.assemblers.security;

import com.studies.foodorders.api.controllers.security.GroupController;
import com.studies.foodorders.api.links.GroupLinks;
import com.studies.foodorders.api.model.security.group.GroupInput;
import com.studies.foodorders.api.model.security.group.GroupModel;
import com.studies.foodorders.domain.models.security.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class GroupModelAssembler extends RepresentationModelAssemblerSupport<Group, GroupModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GroupLinks groupLinks;

    public GroupModelAssembler() {
        super(GroupController.class, GroupModel.class);
    }

    @Override
    public GroupModel toModel(Group group) {

        GroupModel groupModel = createModelWithId(group.getId(), group);
        modelMapper.map(group, groupModel);

        groupModel.add(groupLinks.linkToGroups("groups"));

        groupModel.add(groupLinks.linkToGroupPermissions(group.getId(), "permissions"));

        return groupModel;
    }

    @Override
    public CollectionModel<GroupModel> toCollectionModel(Iterable<? extends Group> entities) {
        return super.toCollectionModel(entities)
                .add(groupLinks.linkToGroups());
    }

    public Group toDomainObject(GroupInput groupInput) {
        return modelMapper.map(groupInput, Group.class);
    }

    public void copyToDomainObject(GroupInput groupInput, Group group) {
        modelMapper.map(groupInput, group);
    }

}
