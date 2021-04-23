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
@Table(name = "hack_appartment_client")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class AppartmentClient {
    @Id
    @GeneratedValue
    @Column(name = "appartment_client_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    @ManyToOne
    @JoinColumn(name = "appartment_id")
    Appartment appartment;

    @Column(name = "date_of_start")
    Date dateOfStart;

    @Column(name = "date_of_end")
    Date dateOfEnd;

    @Column(name = "date_of_sell")
    Date dateOfSell;
}
