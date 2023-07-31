package com.studies.foodorders.domain.services.security;

import com.studies.foodorders.domain.exceptions.PermissionNotFoundException;
import com.studies.foodorders.domain.models.security.Permission;
import com.studies.foodorders.domain.repositories.security.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission findIfExists(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new PermissionNotFoundException(id));
    }

}
