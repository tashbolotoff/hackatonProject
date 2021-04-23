package kg.hackaton.project.repositories;

import kg.hackaton.project.entities.Rayon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RayonRepo extends JpaRepository<Rayon, Long> {
    List<Rayon> getAllByOblastId(Long oblastId);
}
