package kg.hackaton.project.repositories;

import kg.hackaton.project.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
}
