package com.studies.foodorders.domain.services.security;

import com.studies.foodorders.domain.exceptions.GroupNotFoundException;
import com.studies.foodorders.domain.exceptions.UsedEntityException;
import com.studies.foodorders.domain.models.security.Group;
import com.studies.foodorders.domain.models.security.Permission;
import com.studies.foodorders.domain.repositories.security.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    public static final String GROUP_IN_USE = "Group in use.";

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PermissionService permissionService;

    public List<Group> list() {
        return groupRepository.findAll();
    }

    public Optional<Group> find(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        return group;
    }

    @Transactional
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Transactional
    public void delete(Long id) {
        try {
            groupRepository.deleteById(id);
            groupRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new GroupNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new UsedEntityException(
                    String.format(GROUP_IN_USE, id));
        }
    }

    @Transactional
    public void permissionAssociate(Long gruopId, Long permissionId) {
        Group group = findIfExists(gruopId);
        Permission permission = permissionService.findIfExists(permissionId);

        group.addPermission(permission);
    }

    @Transactional
    public void permissionDisassociate(Long gruopId, Long permissionId) {
        Group group = findIfExists(gruopId);
        Permission permission = permissionService.findIfExists(permissionId);

        group.deletePermission(permission);
    }

    public Group findIfExists(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
    }

}
