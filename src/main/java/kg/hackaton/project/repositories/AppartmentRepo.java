package kg.hackaton.project.repositories;

import kg.hackaton.project.entities.Appartment;
import kg.hackaton.project.enums.TypeOfSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppartmentRepo extends JpaRepository<Appartment, Long> {
    List<Appartment> getAllByTypeOfSale(TypeOfSale type);
}
