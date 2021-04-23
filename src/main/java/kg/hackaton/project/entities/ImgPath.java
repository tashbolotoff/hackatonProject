package kg.hackaton.project.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hack_img_path")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ImgPath {
    @Id
    @GeneratedValue
    @Column(name = "img_path_id")
    Long id;

    @Column(name = "path", columnDefinition = "TEXT")
    String path;

    @ManyToOne
    @JoinColumn(name = "appartment_id")
    Appartment appartment;
}
