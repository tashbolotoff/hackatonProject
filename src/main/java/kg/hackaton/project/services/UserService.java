package kg.hackaton.project.services;

import kg.hackaton.project.entities.User;
import kg.hackaton.project.models.UserModel;

import java.util.List;

public interface UserService {
    User create(UserModel userModel);
    List<User> findAll();
    User getById(Long id);
    User update(UserModel userModel);
    User getUserByUsername(String username);
}
