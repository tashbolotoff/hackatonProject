package kg.hackaton.project.services.serviceImpl;

import kg.hackaton.project.entities.UserRole;
import kg.hackaton.project.exceptions.RecordNotFoundException;
import kg.hackaton.project.repositories.UserRoleRepo;
import kg.hackaton.project.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Override
    public UserRole createUserRole(UserRole userRole) {
        return userRoleRepo.save(userRole);
    }

    @Override
    public UserRole getUserRoleById(Long id) {
        return userRoleRepo.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Record not found with id:" + id));
    }

    @Override
    public List<UserRole> getAllUserRoles() {
        return userRoleRepo.findAll();
    }

    @Override
    public UserRole updateUserRole(UserRole userRole) {
        return userRoleRepo.findById(userRole.getId())
                .map(newUserRole -> {
                    newUserRole.setName(userRole.getName());
                    return userRoleRepo.save(newUserRole);
                                })
                .orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id:" + userRole.getId()));

    }

    @Override
    public String deleteUserRoleById(Long id) {
        userRoleRepo.deleteById(id);
        return id + " has been deleted!";
    }
}
