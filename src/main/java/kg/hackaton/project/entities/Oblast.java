package kg.hackaton.project.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hack_oblast")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Oblast extends  Audit<String>{
    @Id
    @GeneratedValue
    @Column(name = "oblast_id")
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "oblast_code")
    Integer oblastCode;
}
