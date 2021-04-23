package kg.hackaton.project.controllers;

import kg.hackaton.project.entities.User;
import kg.hackaton.project.services.AppartmentService;
import kg.hackaton.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    private AppartmentService appartmentService;

    @Autowired
    private UserService userService;

    private User currentUser;

    @GetMapping("/")
    public String getMainPage(Model model) {
        getCurrentUser();
        if (currentUser != null) {
            setUserCredentials(model);
        }
        model.addAttribute("appartments", appartmentService.findAll());
        return "index";
    }
    @GetMapping("/appartment/info/{id}")
    public String getAppartmentInfo(@PathVariable("id") Long id, Model model) {
        getCurrentUser();
        if (currentUser != null) {
            setUserCredentials(model);
        }
        model.addAttribute("appartment", appartmentService.getById(id));
        return "appartment_info";
    }

    @GetMapping("/success")
    public String getMainPage(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin/user/list";
        } else if(request.isUserInRole("ROLE_MANAGER")){
            return "redirect:/admin/appartment/list";
        } else if (request.isUserInRole("ROLE_CLIENT")) {
            return "redirect:/";
        }
        return "redirect:/";
    }

    public void setUserCredentials(Model model){
        getCurrentUser();
        Long id = currentUser.getId();
        model.addAttribute("name", userService.getById(id) != null ? userService.getById(id).getName() : "Имя");
        model.addAttribute("surname", userService.getById(id) != null ? userService.getById(id).getSurname() : "Фамилия");
        model.addAttribute("role", userService.getById(id).getUserRole().getName().equals("ROLE_ADMIN") ? "Администратор" : (currentUser.getUserRole().getName().equals("ROLE_MANAGER") ? "Менеджер" : "Клиент"));
    }

    private void getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() != "anonymousUser") {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            currentUser = userService.getUserByUsername(userDetails.getUsername());
        }
    }
}
