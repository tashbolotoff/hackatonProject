package kg.hackaton.project.services.serviceImpl;

import kg.hackaton.project.entities.Client;
import kg.hackaton.project.exceptions.RecordNotFoundException;
import kg.hackaton.project.models.ClientModel;
import kg.hackaton.project.repositories.AppartmentRepo;
import kg.hackaton.project.repositories.ClientRepo;
import kg.hackaton.project.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private AppartmentRepo appartmentRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Override
    public Client create(ClientModel clientModel) {
        Client client = null;
        try {
            client = Client.builder()
                    .name(clientModel.getName() != null ? clientModel.getName() : null)
                    .surname(clientModel.getSurname() != null ? clientModel.getSurname() : null)
                    .middlename(clientModel.getMiddlename() != null ? clientModel.getMiddlename() : null)
                    .email(clientModel.getEmail() != null ? clientModel.getEmail() : null)
                    .phone(clientModel.getPhone() != null ? clientModel.getPhone() : null)
                    .passportNumber(clientModel.getPassportNumber() != null ? clientModel.getPassportNumber() : null)
                    .pin(clientModel.getPin() != null ? clientModel.getPin() : null)
                    .dateOfBirth(clientModel.getDateOfBirth() != null ? new SimpleDateFormat("yyyy-MM-dd").parse(clientModel.getDateOfBirth()) : null)
                    .appartment(appartmentRepo.getOne(clientModel.getAppartmentId()))
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return clientRepo.save(client);
    }

    @Override
    public Client update(ClientModel clientModel) {
        return clientRepo.findById(clientModel.getClientId())
                .map(newClient -> {
                    newClient.setName(clientModel.getName() != null ? clientModel.getName() : null);
                    newClient.setSurname(clientModel.getSurname() != null ? clientModel.getSurname() : null);
                    newClient.setMiddlename(clientModel.getMiddlename() != null ? clientModel.getMiddlename() : null);
                    newClient.setEmail(clientModel.getEmail() != null ? clientModel.getEmail() : null);
                    newClient.setPhone(clientModel.getPhone() != null ? clientModel.getPhone() : null);
                    newClient.setPassportNumber(clientModel.getPassportNumber() != null ? clientModel.getPassportNumber() : null);
                    newClient.setPin(clientModel.getPin() != null ? clientModel.getPin() : null);
                    try {
                        newClient.setDateOfBirth(clientModel.getDateOfBirth() != null ? new SimpleDateFormat("yyyy-MM-dd").parse(clientModel.getDateOfBirth()) : null);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    newClient.setAppartment(clientModel.getAppartmentId() != null ? appartmentRepo.getOne(clientModel.getAppartmentId()) : null);
                    return clientRepo.save(newClient);
                }).orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id:" + clientModel.getClientId()));
    }

    @Override
    public List<Client> findAll() {
        return clientRepo.findAll();
    }

    @Override
    public Client getById(Long id) {
        return clientRepo.getOne(id);
    }
}
