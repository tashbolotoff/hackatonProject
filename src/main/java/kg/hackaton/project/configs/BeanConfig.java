package kg.hackaton.project.configs;

import kg.hackaton.project.services.serviceImpl.SpringSecurityAuditorAwarServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//spring annotation
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
public class BeanConfig {

    //    private User currentUser;
    //helps to aware the application's current auditor.
    //this is some kind of user mostly.
    @Bean
    public AuditorAware<String> aware() {
        return new SpringSecurityAuditorAwarServiceImpl();
    }

//    public void getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            currentUser = userService.getUserByUsername(userDetails.getUsername());
//        }
//    }
}
