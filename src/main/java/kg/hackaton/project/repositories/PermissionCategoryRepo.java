package kg.hackaton.project.repositories;

import kg.hackaton.project.entities.PermissionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionCategoryRepo extends JpaRepository<PermissionCategory, Long> {
}
