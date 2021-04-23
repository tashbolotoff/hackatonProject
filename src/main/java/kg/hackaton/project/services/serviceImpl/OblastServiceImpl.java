package kg.hackaton.project.services.serviceImpl;

import kg.hackaton.project.entities.Oblast;
import kg.hackaton.project.repositories.OblastRepo;
import kg.hackaton.project.services.OblastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OblastServiceImpl implements OblastService {

    @Autowired
    private OblastRepo oblastRepo;

    @Override
    public List<Oblast> findAll() {
        return oblastRepo.findAll();
    }

    @Override
    public Oblast getById(Long id) {
        return oblastRepo.getOne(id);
    }
}
