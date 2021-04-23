package kg.hackaton.project.repositories;

import kg.hackaton.project.entities.Oblast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OblastRepo extends JpaRepository<Oblast, Long> {
    Oblast getOblastByOblastCode(Integer code);
}
