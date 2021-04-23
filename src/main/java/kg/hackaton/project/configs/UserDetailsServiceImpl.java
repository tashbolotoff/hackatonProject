package kg.hackaton.project.configs;

import kg.hackaton.project.entities.Permission;
import kg.hackaton.project.entities.User;
import kg.hackaton.project.enums.UserStatus;
import kg.hackaton.project.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.getByUsername(username);
        if (user == null || user.getUserStatus() == UserStatus.Деактивирован) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(user));
    }


    private List<GrantedAuthority> mapToGrantedAuthorities(User user) {
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority(user.getUserRole().getName());
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Permission permission : user.getUserRole().getPermissions()){
            authorities.add(new SimpleGrantedAuthority(permission.getName()));
        }
        authorities.add(auth);
        return authorities;
    }

}
