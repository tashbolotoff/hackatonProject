package kg.hackaton.project.services;


import kg.hackaton.project.entities.Rayon;
import kg.hackaton.project.models.RayonModel;

import java.util.List;

public interface RayonService {
    List<Rayon> findAll();
    Rayon getById(Long id);
    Rayon create(RayonModel rayonModel);
    Rayon update(RayonModel rayonModel);
}
