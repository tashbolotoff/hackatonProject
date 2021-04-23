package kg.hackaton.project.controllers;

import kg.hackaton.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @PreAuthorize("isAuthenticated() and hasPermission('USER_READ', 'SUPER_ADMIN')")
    @GetMapping("/user/list")
    public String getAllUsers(Model model) {
        model.addAttribute("title", "Список пользователей");
        model.addAttribute("users", userService.findAll());
        return "users/user-list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('NOTIFICATION_READ', 'SUPER_ADMIN')")
    @GetMapping("/user/list")
    public String getAllNotifications(Model model) {
        model.addAttribute("title", "Журнал извещений");
        return "notifications/notification-list";
    }
}
