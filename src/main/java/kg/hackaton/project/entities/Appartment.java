package kg.hackaton.project.entities;

import kg.hackaton.project.enums.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hack_appartment")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Appartment extends Audit<String>{
    @Id
    @GeneratedValue
    @Column(name = "appartment_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "rayon_id")
    Rayon rayon;

    @Column(name = "count_of_rooms", nullable = false)
    Integer countOfRooms;

    @Column(name = "stage", nullable = false)
    Integer stage;

    @Column(name = "price", nullable = false)
    BigDecimal price;

    @Column(name = "address", nullable = false)
    String address;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "serie_id")
    Serie serie;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition")
    Condition condition;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_house")
    TypeOfHouse typeOfHouse;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_sale")
    TypeOfSale typeOfSale;

    @Enumerated(EnumType.STRING)
    @Column(name = "busy_or_free_status")
    BusyOrFreeStatus busyOrFreeStatus;

    @NumberFormat(pattern = "###.######")
    @Column(name = "latitude")
    Double latitude;

    @NumberFormat(pattern = "###.######")
    @Column(name = "longitude")
    Double longitude;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "imgPaths",
            joinColumns = @JoinColumn(name = "appartment_id"),
            inverseJoinColumns = @JoinColumn(name = "img_path_id"))
    List<ImgPath> imgPaths ;

}
