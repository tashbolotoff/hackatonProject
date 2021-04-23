package kg.hackaton.project.repositories;

import kg.hackaton.project.entities.Appartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppartmentRepo extends JpaRepository<Appartment, Long> {
}
