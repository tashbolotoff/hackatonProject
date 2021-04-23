package kg.hackaton.project.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hack_rayon")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Rayon extends  Audit<String>{
    @Id
    @GeneratedValue
    @Column(name = "rayon_id")
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "oblast_id")
    Oblast oblast;
}