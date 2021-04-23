package kg.hackaton.project.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "hack_user")

public class User{

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    UserRole userRole;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "name")
    String name;

    @Column(name = "surname")
    String surname;

    @Column(name = "middleName")
    String middleName;

    @Column(name = "email")
    String email;

    @Column(name = "phone")
    String phone;

    @Column(name = "date_of_birth")
    Date dateOfBirth;


}