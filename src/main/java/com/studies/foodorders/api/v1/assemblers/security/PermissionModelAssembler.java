package com.studies.foodorders.api.v1.assemblers.security;

import com.studies.foodorders.api.v1.links.PermissionLinks;
import com.studies.foodorders.api.v1.models.security.permission.PermissionModel;
import com.studies.foodorders.domain.models.security.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PermissionModelAssembler implements RepresentationModelAssembler<Permission, PermissionModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PermissionLinks permissionLinks;

    public PermissionModel toModel(Permission permission) {
        return modelMapper.map(permission, PermissionModel.class);
    }

    @Override
    public CollectionModel<PermissionModel> toCollectionModel(Iterable<? extends Permission> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities)
                .add(permissionLinks.linkToPermissions());
    }

}
