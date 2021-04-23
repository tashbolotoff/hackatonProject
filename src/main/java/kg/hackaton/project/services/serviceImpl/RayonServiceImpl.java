package kg.hackaton.project.services.serviceImpl;

import kg.hackaton.project.entities.Rayon;
import kg.hackaton.project.exceptions.RecordNotFoundException;
import kg.hackaton.project.models.RayonModel;
import kg.hackaton.project.repositories.OblastRepo;
import kg.hackaton.project.repositories.RayonRepo;
import kg.hackaton.project.services.RayonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RayonServiceImpl implements RayonService {

    @Autowired
    private RayonRepo rayonRepo;

    @Autowired
    private OblastRepo oblastRepo;

    @Override
    public List<Rayon> findAll() {
        return rayonRepo.findAll();
    }

    @Override
    public Rayon getById(Long id) {
        return rayonRepo.getOne(id);
    }

    @Override
    public Rayon create(RayonModel rayonModel) {
        Rayon rayon = Rayon.builder()
                .name(rayonModel.getName())
                .oblast(oblastRepo.getOne(rayonModel.getOblastId()))
                .build();
        return rayonRepo.save(rayon);
    }

    @Override
    public Rayon update(RayonModel rayonModel) {
        return rayonRepo.findById(rayonModel.getId())
                .map(newRayon -> {
                    newRayon.setName(rayonModel.getName());
                    newRayon.setOblast(oblastRepo.getOne(rayonModel.getOblastId()));
                    return rayonRepo.save(newRayon);
                }).orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id " + rayonModel.getId()));
    }


}
