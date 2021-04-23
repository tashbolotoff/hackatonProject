package kg.hackaton.project.services.serviceImpl;

import kg.hackaton.project.entities.ImgPath;
import kg.hackaton.project.exceptions.RecordNotFoundException;
import kg.hackaton.project.models.ImgPathModel;
import kg.hackaton.project.repositories.AppartmentRepo;
import kg.hackaton.project.repositories.ImgPathRepo;
import kg.hackaton.project.services.ImgPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgServiceImpl implements ImgPathService {

    @Autowired
    private AppartmentRepo appartmentRepo;

    @Autowired
    private ImgPathRepo imgPathRepo;

    @Override
    public ImgPath create(ImgPathModel imgPathModel) {
        ImgPath imgPath = ImgPath.builder()
                .path(imgPathModel.getPath() != null ? imgPathModel.getPath() : null)
                .build();
        return imgPathRepo.save(imgPath);
    }

    @Override
    public ImgPath update(ImgPathModel imgPathModel) {
        return imgPathRepo.findById(imgPathModel.getImgPathId())
                .map(newImgPath -> {
                    newImgPath.setPath(imgPathModel.getPath());
                    return imgPathRepo.save(newImgPath);
                }).orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id:"+imgPathModel.getImgPathId()));
    }

    @Override
    public List<ImgPath> findAll() {
        return imgPathRepo.findAll();
    }

    @Override
    public ImgPath getById(Long id) {
        return imgPathRepo.getOne(id);
    }
}
