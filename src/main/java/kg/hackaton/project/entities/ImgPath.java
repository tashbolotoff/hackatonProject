package kg.hackaton.project.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hack_img_path")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ImgPath extends Audit<String> {
    @Id
    @GeneratedValue
    @Column(name = "img_path_id")
    Long id;

    @Column(name = "path", columnDefinition = "TEXT")
    String path;

    @ManyToMany(mappedBy = "imgPaths", fetch = FetchType.EAGER)
    List<Appartment> appartments;


}
