package kg.hackaton.project.controllers;

import kg.hackaton.project.entities.User;
import kg.hackaton.project.models.ClientModel;
import kg.hackaton.project.services.ClientService;
import kg.hackaton.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        return "clients/registration";
    }

    @PostMapping("/registration")
    public String registration(@Validated @ModelAttribute("client") ClientModel clientModel) {
        clientService.create(clientModel);
        return "redirect:/login";

    }

}
