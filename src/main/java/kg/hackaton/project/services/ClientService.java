package kg.hackaton.project.services;

import kg.hackaton.project.entities.Client;
import kg.hackaton.project.models.ClientModel;

import java.util.List;

public interface ClientService {
    Client create(ClientModel clientModel);
    Client update(ClientModel clientModel);
    List<Client> findAll();
    Client getById(Long id);
}
