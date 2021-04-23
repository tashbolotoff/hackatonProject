package kg.hackaton.project.services;

import kg.hackaton.project.entities.Oblast;

import java.util.List;

public interface OblastService {

    List<Oblast> findAll();
    Oblast getById(Long id);
}
