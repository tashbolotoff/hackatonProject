package kg.hackaton.project.services.serviceImpl;

import kg.hackaton.project.entities.User;
import kg.hackaton.project.enums.UserStatus;
import kg.hackaton.project.exceptions.RecordNotFoundException;
import kg.hackaton.project.models.UserModel;
import kg.hackaton.project.repositories.UserRepo;
import kg.hackaton.project.repositories.UserRoleRepo;
import kg.hackaton.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    UserRoleRepo userRoleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User create(UserModel userModel){
        User user = null;
        try {
            user = User.builder()
                    .username(userModel.getUsername())
                    .password(passwordEncoder.encode(userModel.getPassword()))
                    .userRole(userRoleRepo.getOne(userModel.getUserRoleId()))
                    .name(userModel.getName() != null ? userModel.getName() : null)
                    .surname(userModel.getSurname() != null ? userModel.getSurname() : null)
                    .middleName(userModel.getMiddleName() != null ? userModel.getMiddleName() : null)
                    .email(userModel.getEmail() != null ? userModel.getEmail() : null)
                    .phone(userModel.getPhone() != null ? userModel.getPhone() : null)
                    .dateOfBirth(userModel.getDateOfBirth() != null ? new SimpleDateFormat("yyyy-MM-dd").parse(userModel.getDateOfBirth()) : null)
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return userRepo.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepo.getOne(id);
    }

    @Override
    public User update(UserModel userModel){
        return userRepo.findById(userModel.getId())
            .map(newUser -> {
                newUser.setUsername(userModel.getUsername());
                newUser.setPassword(passwordEncoder.encode(userModel.getPassword()));
                newUser.setUserRole(userRoleRepo.getOne(userModel.getUserRoleId()));
                newUser.setName(userModel.getName() != null ? userModel.getName() : null);
                newUser.setSurname(userModel.getSurname() != null ? userModel.getSurname() : null);
                newUser.setMiddleName(userModel.getMiddleName() != null ? userModel.getMiddleName() : null);
                newUser.setEmail(userModel.getEmail() != null ? userModel.getEmail() : null);
                newUser.setPhone(userModel.getPhone() != null ? userModel.getPhone() : null);
                try {
                    newUser.setDateOfBirth(userModel.getDateOfBirth() != null ? new SimpleDateFormat("yyyy-MM-dd").parse(userModel.getDateOfBirth()) : null);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return userRepo.save(newUser);
            })
            .orElseThrow(() ->
                    new RecordNotFoundException("Record not found with id:" + userModel.getId()));
    }

    @Override
    public User changeUserStatus(Long userId) {
        return userRepo.findById(userId)
                .map(newUser -> {
                    newUser.setUserStatus(userRepo.getOne(userId).getUserStatus() == UserStatus.АКТИВИРОВАН ? UserStatus.ДЕАКТИВИРОВАН : UserStatus.АКТИВИРОВАН);
                    return userRepo.save(newUser);
                }).
                        orElseThrow(() ->
                                new RecordNotFoundException("Record not found with id:" + userId));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.getByUsername(username);
    }
}
