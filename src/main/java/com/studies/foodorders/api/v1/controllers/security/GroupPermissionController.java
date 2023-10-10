package com.studies.foodorders.api.v1.controllers.security;

import com.studies.foodorders.api.v1.assemblers.security.PermissionModelAssembler;
import com.studies.foodorders.api.v1.links.GroupLinks;
import com.studies.foodorders.api.v1.models.security.permission.PermissionModel;
import com.studies.foodorders.api.v1.openapi.controllers.GroupPermissionControllerOpenApi;
import com.studies.foodorders.domain.models.security.Group;
import com.studies.foodorders.domain.services.security.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/groups/{groupId}/permissions")
public class GroupPermissionController implements GroupPermissionControllerOpenApi {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionModelAssembler permissionModelAssembler;

    @Autowired
    private GroupLinks groupLinks;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<PermissionModel> list(@PathVariable Long groupId) {
        Group group = groupService.findIfExists(groupId);

        CollectionModel<PermissionModel> permissionsModel =
                permissionModelAssembler.toCollectionModel(group.getPermissions())
                        .removeLinks()
                        .add(groupLinks.linkToGroupPermissions(groupId))
                        .add(groupLinks.linkToGroupPermissionAssociate(groupId, "associate"));

        permissionsModel.getContent().forEach(permissionModel -> {
            permissionModel.add(groupLinks.linkToGroupPermissionDisassociate(
                    groupId, permissionModel.getId(), "disassociate"));
        });

        return permissionsModel;
    }

    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associate(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.permissionAssociate(groupId, permissionId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disassociate(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.permissionDisassociate(groupId, permissionId);

        return ResponseEntity.noContent().build();
    }

}
