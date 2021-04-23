package kg.hackaton.project.configs;

import kg.socservice.epidPlatform.entities.Permission;
import kg.socservice.epidPlatform.entities.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
public class UserPrincipal implements UserDetails {
    private Long id;
    private String name;
    private String email;
    private Collection<? extends GrantedAuthority> roles;
    private Collection<? extends GrantedAuthority> permissions;

    public static UserPrincipal createUserPrincipal(User user) {
        if (user != null) {
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority(user.getUserRole().getName()));

            List<GrantedAuthority> permissions = new ArrayList<>();
            for(Permission permission : user.getUserRole().getPermissions()){
                permissions.add(new SimpleGrantedAuthority(permission.getName()));
            }

            return UserPrincipal.builder()
                    .id(user.getId())
                    .name(user.getUsername())
                    .roles(roles)
                    .permissions(permissions)
                    .build();
        }
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
