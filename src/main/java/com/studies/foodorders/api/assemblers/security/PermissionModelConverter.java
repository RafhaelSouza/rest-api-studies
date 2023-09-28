package com.studies.foodorders.api.assemblers.security;

import com.studies.foodorders.api.model.security.permission.PermissionModel;
import com.studies.foodorders.domain.models.security.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissionModelConverter {

    @Autowired
    private ModelMapper modelMapper;

    public PermissionModel toModel(Permission permission) {
        return modelMapper.map(permission, PermissionModel.class);
    }

    public List<PermissionModel> toCollectionModel(Collection<Permission> permissions) {
        return permissions.stream()
                .map(permission -> toModel(permission))
                .collect(Collectors.toList());
    }

}
