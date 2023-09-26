package com.studies.foodorders.api.controllers.security;

import com.studies.foodorders.api.converter.security.PermissionModelConverter;
import com.studies.foodorders.api.model.security.permission.PermissionModel;
import com.studies.foodorders.api.openapi.controllers.GroupPermissionControllerOpenApi;
import com.studies.foodorders.domain.models.security.Group;
import com.studies.foodorders.domain.services.security.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/groups/{groupId}/permissions")
public class GroupPermissionController implements GroupPermissionControllerOpenApi {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionModelConverter permissionModelConverter;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PermissionModel> list(@PathVariable Long groupId) {
        Group group = groupService.findIfExists(groupId);

        return permissionModelConverter.toCollectionModel(group.getPermissions());
    }

    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.permissionAssociate(groupId, permissionId);
    }

    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.permissionDisassociate(groupId, permissionId);
    }

}
