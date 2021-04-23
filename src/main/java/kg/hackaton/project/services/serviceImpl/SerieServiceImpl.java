package kg.hackaton.project.services.serviceImpl;

import kg.hackaton.project.entities.Serie;
import kg.hackaton.project.exceptions.RecordNotFoundException;
import kg.hackaton.project.models.SerieModel;
import kg.hackaton.project.repositories.SerieRepo;
import kg.hackaton.project.services.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieServiceImpl implements SerieService {

    @Autowired
    private SerieRepo serieRepo;

    @Override
    public Serie create(SerieModel serieModel) {
        Serie serie = Serie.builder()
                .name(serieModel.getName() != null ? serieModel.getName() : null)
                .build();
        return serieRepo.save(serie);
    }

    @Override
    public Serie update(SerieModel serieModel) {
        return serieRepo.findById(serieModel.getSerieId())
                .map(newSerie -> {
                    newSerie.setName(serieModel.getName() != null ? serieModel.getName() : null);
                    return serieRepo.save(newSerie);
                }).orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id:"+serieModel.getSerieId()));
    }

    @Override
    public List<Serie> findAll() {
        return serieRepo.findAll();
    }

    @Override
    public Serie getById(Long id) {
        return serieRepo.getOne(id);
    }

    @Override
    public String delete(Long id) {
        serieRepo.deleteById(id);
        return "Serie with id "+id+" has been deleted";
    }
}
