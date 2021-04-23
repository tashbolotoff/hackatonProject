package kg.hackaton.project.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hack_manufacturer")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Manufacturer extends Audit<String>{
    @Id
    @GeneratedValue
    @Column(name = "manufacturer_id")
    Long id;

    @Column(name = "name", nullable = false)
    String name;
}
