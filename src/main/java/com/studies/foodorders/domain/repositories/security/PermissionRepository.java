package com.studies.foodorders.domain.repositories.security;

import com.studies.foodorders.domain.models.security.Permission;

import java.util.List;

public interface PermissionRepository {

    List<Permission> list();
    Permission find(Long id);
    Permission save(Permission kitchen);
    void delete(Permission kitchen);

}
