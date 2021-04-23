package kg.hackaton.project.repositories;

import kg.hackaton.project.entities.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepo extends JpaRepository<Serie, Long> {
}
