package kg.hackaton.project.models;

import kg.hackaton.project.entities.Manufacturer;
import kg.hackaton.project.entities.Rayon;
import kg.hackaton.project.enums.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppartmentModel {
    Long id;

    Long rayonId;

    Integer countOfRooms;

    Integer stage;

    BigDecimal price;

    String address;

    Long manufacturerId;

    Long serieId;

    String condition;

    String typeOfHouse;

    String typeOfSale;

    String busyOrFreeStatus;

    Double latitude;

    Double longitude;

    List<MultipartFile> imgPaths;
}
