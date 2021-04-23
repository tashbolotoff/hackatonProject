package kg.hackaton.project.controllers;

import kg.hackaton.project.models.UserModel;
import kg.hackaton.project.services.UserRoleService;
import kg.hackaton.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @PreAuthorize("isAuthenticated() and hasPermission('USER_READ', 'SUPER_ADMIN')")
    @GetMapping("/user/list")
    public String getAllUsers(Model model) {
        model.addAttribute("title", "Список пользователей");
        model.addAttribute("users", userService.findAll());
        return "users/user_list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('USER_CREATE', 'SUPER_ADMIN')")
    @GetMapping("/user/add")
    public String getUserAddForm(Model model){
        model.addAttribute("title", "Добавление нового пользователя");
        model.addAttribute("roles", userRoleService.getAllUserRoles());
        return "users/user_edit_form";
    }

//    @PreAuthorize("isAuthenticated() and hasPermission('USER_CREATE', 'SUPER_ADMIN')")
//    @PostMapping(value = "/user/add")
//    public String addEmployee(@ModelAttribute("UserModel") UserModel userModel) {
//        employeeService.create(employeeModel);
//        return "redirect:/admin/user/list";
//    }
}
