package kg.hackaton.project.repositories;

import kg.hackaton.project.entities.AppartmentClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppartmentClientRepo extends JpaRepository<AppartmentClient, Long> {
}
