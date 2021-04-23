package kg.hackaton.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hack_permission")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "name_ru")
    private String nameRu;

    @JsonBackReference
    @ManyToOne(optional = true)
    @JoinColumn(name = "permission_category_id")
    PermissionCategory permissionCategory;
}
