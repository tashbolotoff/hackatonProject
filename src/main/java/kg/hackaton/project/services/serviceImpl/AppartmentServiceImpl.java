package kg.hackaton.project.services.serviceImpl;

import kg.hackaton.project.entities.Appartment;
import kg.hackaton.project.entities.ImgPath;
import kg.hackaton.project.enums.*;
import kg.hackaton.project.exceptions.RecordNotFoundException;
import kg.hackaton.project.models.AppartmentModel;
import kg.hackaton.project.repositories.*;
import kg.hackaton.project.services.AppartmentService;
import kg.hackaton.project.utils.UtilBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppartmentServiceImpl implements AppartmentService {
    @Autowired
    private AppartmentRepo appartmentRepo;

    @Autowired
    private ManufacturerRepo manufacturerRepo;

    @Autowired
    private RayonRepo rayonRepo;

    @Autowired
    private SerieRepo serieRepo;

    @Autowired
    private ImgPathRepo imgPathRepo;


    @Override
    public List<Appartment> findAll() {
        return appartmentRepo.findAll();
    }

    @Override
    public Appartment create(AppartmentModel appartmentModel) {
        Appartment appartment = Appartment.builder()
                .rayon(rayonRepo.getOne(appartmentModel.getRayonId()) != null ? rayonRepo.getOne(appartmentModel.getRayonId()) : null)
                .countOfRooms(appartmentModel.getCountOfRooms() != null ? appartmentModel.getCountOfRooms() : null)
                .stage(appartmentModel.getStage() != null ? appartmentModel.getStage() : null)
                .price(appartmentModel.getPrice() != null ? appartmentModel.getPrice() : null)
                .address(appartmentModel.getAddress() != null ? appartmentModel.getAddress() : null)
                .manufacturer(manufacturerRepo.getOne(appartmentModel.getManufacturerId()) != null ? manufacturerRepo.getOne(appartmentModel.getManufacturerId()) : null)
                .serie(appartmentModel.getSerieId() != null ? serieRepo.getOne(appartmentModel.getSerieId()) : null)
                .condition(appartmentModel.getCondition() != null ? Condition.valueOf(appartmentModel.getCondition()) : null)
                .typeOfHouse(appartmentModel.getTypeOfHouse() != null ? TypeOfHouse.valueOf(appartmentModel.getTypeOfHouse()) : null)
                .typeOfSale(appartmentModel.getTypeOfSale() != null ? TypeOfSale.valueOf(appartmentModel.getTypeOfSale()) : null)
                .busyOrFreeStatus(appartmentModel.getBusyOrFreeStatus() != null ? BusyOrFreeStatus.valueOf(appartmentModel.getBusyOrFreeStatus()) : null)
                .latitude(appartmentModel.getLatitude() != null ? appartmentModel.getLatitude() : null)
                .longitude(appartmentModel.getLongitude() != null ? appartmentModel.getLongitude() : null)
                .build();
        List<ImgPath> imgPaths = new ArrayList<>();
        if (!appartmentModel.getImgPaths().isEmpty()) {
            for (MultipartFile multipartFile : appartmentModel.getImgPaths()) {
                if (multipartFile != null && multipartFile.getContentType().contains("image")) {
                    String base64 = UtilBase64.encoder(multipartFile);
                    ImgPath imgPath = ImgPath.builder()
                            .path(base64)
                            .build();
                    imgPaths.add(imgPathRepo.save(imgPath));
                }
            }
        }
        appartment.setImgPaths(imgPaths);
        return appartmentRepo.save(appartment);
    }

    @Override
    public Appartment update(AppartmentModel appartmentModel) {
        return appartmentRepo.findById(appartmentModel.getId())
                .map(newAppartment -> {
                    newAppartment.setRayon(rayonRepo.getOne(appartmentModel.getRayonId()) != null ? rayonRepo.getOne(appartmentModel.getRayonId()) : null);
                    newAppartment.setCountOfRooms(appartmentModel.getCountOfRooms() != null ? appartmentModel.getCountOfRooms() : null);
                    newAppartment.setStage(appartmentModel.getStage() != null ? appartmentModel.getStage() : null);
                    newAppartment.setPrice(appartmentModel.getPrice() != null ? appartmentModel.getPrice() : null);
                    newAppartment.setAddress(appartmentModel.getAddress() != null ? appartmentModel.getAddress() : null);
                    newAppartment.setManufacturer(manufacturerRepo.getOne(appartmentModel.getManufacturerId()) != null ? manufacturerRepo.getOne(appartmentModel.getManufacturerId()) : null);
                    newAppartment.setSerie(appartmentModel.getSerieId() != null ? serieRepo.getOne(appartmentModel.getSerieId()) : null);
                    newAppartment.setCondition(appartmentModel.getCondition() != null ? Condition.valueOf(appartmentModel.getCondition()) : null);
                    newAppartment.setTypeOfHouse(appartmentModel.getTypeOfHouse() != null ? TypeOfHouse.valueOf(appartmentModel.getTypeOfHouse()) : null);
                    newAppartment.setTypeOfSale(appartmentModel.getTypeOfSale() != null ? TypeOfSale.valueOf(appartmentModel.getTypeOfSale()) : null);
                    newAppartment.setBusyOrFreeStatus(appartmentModel.getBusyOrFreeStatus() != null ? BusyOrFreeStatus.valueOf(appartmentModel.getBusyOrFreeStatus()) : null);
                    newAppartment.setLatitude(appartmentModel.getLatitude() != null ? appartmentModel.getLatitude() : null);
                    newAppartment.setLongitude(appartmentModel.getLongitude() != null ? appartmentModel.getLongitude() : null);
                    return appartmentRepo.save(newAppartment);
                }).orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id " + appartmentModel.getId()));
    }

    @Override
    public String delete(Long id) {
        appartmentRepo.deleteById(id);
        return "Appartment with id " + id + " has been deleted";
    }

    @Override
    public Appartment getById(Long id) {
        return appartmentRepo.getOne(id);
    }
}
