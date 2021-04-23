package kg.hackaton.project.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientModel {

    Long clientId;

    String name;

    String surname;

    String middlename;

    String email;

    String phone;

    String passportNumber;

    String pin;

    String dateOfBirth;

    Appartment appartment;
}
