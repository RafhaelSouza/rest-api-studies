package com.studies.foodorders.infrastructure.repositories.security;

import com.studies.foodorders.domain.models.security.Permission;
import com.studies.foodorders.domain.repositories.security.PermissionRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionRepositoryImpl implements PermissionRepository {

    @Override
    public List<Permission> list() {
        return null;
    }

    @Override
    public Permission find(Long id) {
        return null;
    }

    @Override
    public Permission save(Permission kitchen) {
        return null;
    }

    @Override
    public void delete(Permission kitchen) {

    }

}
