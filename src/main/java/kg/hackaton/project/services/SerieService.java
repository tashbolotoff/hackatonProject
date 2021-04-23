package kg.hackaton.project.services;


import kg.hackaton.project.entities.Serie;
import kg.hackaton.project.models.SerieModel;

import java.util.List;

public interface SerieService {
    Serie create(SerieModel serieModel);
    Serie update(SerieModel serieModel);
    List<Serie> findAll();
    Serie getById(Long id);
    String delete(Long id);
}
