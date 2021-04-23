package kg.hackaton.project.services;

import kg.hackaton.project.models.PermissionModel;

public interface PermissionService {
    void grantSelectedPrivileges(PermissionModel permissionModel);
}
