package kg.hackaton.project.services;

import kg.hackaton.project.entities.AppartmentClient;
import kg.hackaton.project.models.AppartmentClientModel;

import java.util.List;

public interface AppartmentClientService {
    List<AppartmentClient> findAll();

    AppartmentClient create(AppartmentClientModel appartmentClientModel);
}
