package kg.hackaton.project.services;

import kg.hackaton.project.entities.UserRole;

import java.util.List;

public interface UserRoleService {
    //CRUD
    UserRole createUserRole(UserRole userRole);
    UserRole getUserRoleById(Long id);
    List<UserRole> getAllUserRoles();
    UserRole updateUserRole(UserRole userRole);
    String deleteUserRoleById(Long id);
}
