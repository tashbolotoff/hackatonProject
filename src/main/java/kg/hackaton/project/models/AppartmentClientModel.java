package kg.hackaton.project.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppartmentClientModel {
    Long clientId;

    Long appartmentId;

    String dateOfStart;

    String dateOfEnd;

    String dateOfSell;

    String typeOfSale;
}
