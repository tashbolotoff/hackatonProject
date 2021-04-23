package kg.hackaton.project.entities;

import kg.hackaton.project.enums.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "serie")
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
}