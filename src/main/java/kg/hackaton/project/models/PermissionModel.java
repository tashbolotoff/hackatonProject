package kg.hackaton.project.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionModel {
    Long roleId;

    List<PermissionBoolModel> permissionBools;
}
