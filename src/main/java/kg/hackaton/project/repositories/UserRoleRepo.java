package kg.hackaton.project.repositories;

import kg.hackaton.project.entities.User;
import kg.hackaton.project.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepo extends JpaRepository<UserRole, Long> {
}
