package kg.hackaton.project.services.serviceImpl;

import kg.hackaton.project.entities.Permission;
import kg.hackaton.project.entities.UserRole;
import kg.hackaton.project.models.PermissionBoolModel;
import kg.hackaton.project.models.PermissionModel;
import kg.hackaton.project.repositories.PermissionRepo;
import kg.hackaton.project.repositories.UserRoleRepo;
import kg.hackaton.project.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private PermissionRepo privilegeRepo;

    @Override
    public void grantSelectedPrivileges(PermissionModel permissionModel) {
        UserRole userRole = userRoleRepo.getOne(permissionModel.getRoleId());
        ArrayList<Permission> permissionArrayList = new ArrayList<>();
        for(PermissionBoolModel permissionBoolModel : permissionModel.getPermissionBools()){
            if(permissionBoolModel.getPermissionBool()){
                Permission permission = privilegeRepo.getOne(permissionBoolModel.getPermId());
                permissionArrayList.add(permission);
            }
        }
        userRole.setPermissions(permissionArrayList);
        userRoleRepo.save(userRole);
    }
}
