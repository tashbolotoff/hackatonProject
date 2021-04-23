package kg.hackaton.project.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hack_serie")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Serie {

    @Id
    @GeneratedValue
    @Column(name = "serie_id")
    Long id;

    @Column(name = "name")
    String name;
}
