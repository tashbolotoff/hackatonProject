package kg.hackaton.project.services.serviceImpl;

import kg.hackaton.project.entities.Manufacturer;
import kg.hackaton.project.exceptions.RecordNotFoundException;
import kg.hackaton.project.models.ManufacturerModel;
import kg.hackaton.project.repositories.ManufacturerRepo;
import kg.hackaton.project.services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    private ManufacturerRepo manufacturerRepo;

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepo.findAll();
    }

    @Override
    public Manufacturer create(ManufacturerModel manufacturerModel) {
        Manufacturer manufacturer = Manufacturer.builder()
                .name(manufacturerModel.getName())
                .build();
        return manufacturerRepo.save(manufacturer);
    }

    @Override
    public Manufacturer update(ManufacturerModel manufacturerModel) {
        return manufacturerRepo.findById(manufacturerModel.getId())
                .map(newManufacturer -> {
                    newManufacturer.setName(manufacturerModel.getName());
                    return manufacturerRepo.save(newManufacturer);
                }).orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id " + manufacturerModel.getId()));
    }

    @Override
    public String delete(Long id) {
        manufacturerRepo.deleteById(id);
        return "Manufacturer with id "+ id + " has been deleted";
    }

    @Override
    public Manufacturer getById(Long id) {
        return manufacturerRepo.getOne(id);
    }
}
