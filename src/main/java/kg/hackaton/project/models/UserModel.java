package kg.hackaton.project.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserModel {

    Long  userRoleId;

    String username;

    String password;

    String name;

    String surname;

    String middleName;

    String email;

    String phone;

    String dateOfBirth;
}
