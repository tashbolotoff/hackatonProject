package kg.hackaton.project.services;

import kg.hackaton.project.entities.Manufacturer;
import kg.hackaton.project.models.ManufacturerModel;

import java.util.List;

public interface ManufacturerService {
    List<Manufacturer> findAll();

    Manufacturer create(ManufacturerModel manufacturerModel);

    Manufacturer update(ManufacturerModel manufacturerModel);

    String delete(Long id);
}
