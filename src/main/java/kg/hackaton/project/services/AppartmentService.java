package kg.hackaton.project.services;

import kg.hackaton.project.entities.Appartment;
import kg.hackaton.project.models.AppartmentModel;

import java.util.List;

public interface AppartmentService {
    List<Appartment> findAll();

    Appartment create(AppartmentModel appartmentModel);

    Appartment update(AppartmentModel appartmentModel);

    String delete(Long id);

    Appartment getById(Long id);
}
