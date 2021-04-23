package kg.hackaton.project.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @GetMapping("/")
    public String getMainPage(Model model) {
        return "index";
    }

    @GetMapping("/success")
    public String getMainPage(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/";
        } else if(request.isUserInRole("ROLE_MANAGER")){
            return "redirect:/";
        }
        return "redirect:/";
    }

    private void getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() != "anonymousUser") {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        }
    }
}
