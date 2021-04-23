package kg.hackaton.project.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hack_img_path")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Client extends Audit<String> {

    @Id
    @GeneratedValue
    @Column(name = "client_id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "surname")
    String surname;

    @Column(name = "middlename")
    String middlename;

    @Column(name = "email")
    String email;

    @Column(name = "phone")
    String phone;

    @Column(name = "passport_number")
    String passportNumber;

    @Column(name = "pin")
    String pin;

    @Column(name = "date_of_birth")
    Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "appartment_id")
    Appartment appartment;
}
