package kg.hackaton.project.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RayonModel {
    Long id;

    String name;

    Long oblastId;

    Integer rayonCode;

}
