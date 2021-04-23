package kg.hackaton.project.services.serviceImpl;

import kg.hackaton.project.entities.Appartment;
import kg.hackaton.project.entities.AppartmentClient;
import kg.hackaton.project.enums.TypeOfSale;
import kg.hackaton.project.models.AppartmentClientModel;
import kg.hackaton.project.repositories.AppartmentClientRepo;
import kg.hackaton.project.repositories.AppartmentRepo;
import kg.hackaton.project.repositories.ClientRepo;
import kg.hackaton.project.services.AppartmentClientService;
import kg.hackaton.project.services.AppartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class AppartmentClientServiceImpl implements AppartmentClientService {
    @Autowired
    private AppartmentRepo appartmentRepo;

    @Autowired
    private AppartmentService appartmentService;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private AppartmentClientRepo appartmentClientRepo;

    @Override
    public List<AppartmentClient> findAll() {
        return appartmentClientRepo.findAll();
    }

    @Override
    public AppartmentClient create(AppartmentClientModel appartmentClientModel) {
        Appartment appartment = appartmentRepo.getOne(appartmentClientModel.getAppartmentId());
        AppartmentClient appartmentClient = AppartmentClient.builder()
                .appartment(appartment)
                .client(clientRepo.getOne(appartmentClientModel.getClientId()))
                .build();
        try {
            appartmentClient.setDateOfStart(appartmentClientModel.getDateOfStart() != null ? new SimpleDateFormat("yyyy-MM-dd").parse(appartmentClientModel.getDateOfStart()) : null);
            appartmentClient.setDateOfEnd(appartmentClientModel.getDateOfEnd() != null ? new SimpleDateFormat("yyyy-MM-dd").parse(appartmentClientModel.getDateOfEnd()) : null);
            appartmentClient.setDateOfSell(appartmentClientModel.getDateOfSell() != null ? new SimpleDateFormat("yyyy-MM-dd").parse(appartmentClientModel.getDateOfSell()) : null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        appartment.setTypeOfSale(appartmentClientModel.getTypeOfSale() != null ? TypeOfSale.valueOf(appartmentClientModel.getTypeOfSale()) : null);
        appartmentRepo.save(appartment);
        return appartmentClientRepo.save(appartmentClient);
    }
}
