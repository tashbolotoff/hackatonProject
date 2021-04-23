package kg.hackaton.project.services;

import kg.hackaton.project.entities.ImgPath;
import kg.hackaton.project.models.ImgPathModel;

import java.util.List;

public interface ImgPathService {

    ImgPath create(ImgPathModel imgPathModel);
    ImgPath update(ImgPathModel imgPathModel);
    List<ImgPath> findAll();
    ImgPath getById(Long id);
}
