package kg.hackaton.project.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImgPathModel {

    Long imgPathId;

    String path;

    Appartment appartment;
}