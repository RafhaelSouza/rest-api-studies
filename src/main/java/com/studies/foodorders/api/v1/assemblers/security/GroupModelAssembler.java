package com.studies.foodorders.api.v1.assemblers.security;

import com.studies.foodorders.api.v1.controllers.security.GroupController;
import com.studies.foodorders.api.v1.links.GroupLinks;
import com.studies.foodorders.api.v1.models.security.group.GroupInput;
import com.studies.foodorders.api.v1.models.security.group.GroupModel;
import com.studies.foodorders.core.security.ApiSecurity;
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

    @Autowired
    private ApiSecurity apiSecurity;

    public GroupModelAssembler() {
        super(GroupController.class, GroupModel.class);
    }

    @Override
    public GroupModel toModel(Group group) {

        GroupModel groupModel = createModelWithId(group.getId(), group);
        modelMapper.map(group, groupModel);

        if (apiSecurity.isAllowedToSearchUsersGroupsPermissions()) {
            groupModel.add(groupLinks.linkToGroups("groups"));
            groupModel.add(groupLinks.linkToGroupPermissions(group.getId(), "permissions"));
        }

        return groupModel;
    }

    @Override
    public CollectionModel<GroupModel> toCollectionModel(Iterable<? extends Group> entities) {
        CollectionModel<GroupModel> groupModelCollectionModel = super.toCollectionModel(entities);

        if (apiSecurity.isAllowedToSearchUsersGroupsPermissions())
            groupModelCollectionModel.add(groupLinks.linkToGroups());

        return groupModelCollectionModel;
    }

    public Group toDomainObject(GroupInput groupInput) {
        return modelMapper.map(groupInput, Group.class);
    }

    public void copyToDomainObject(GroupInput groupInput, Group group) {
        modelMapper.map(groupInput, group);
    }

}
