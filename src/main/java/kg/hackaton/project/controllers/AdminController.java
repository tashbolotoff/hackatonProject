package kg.hackaton.project.controllers;

import kg.hackaton.project.entities.User;
import kg.hackaton.project.models.ManufacturerModel;
import kg.hackaton.project.services.*;
import kg.hackaton.project.models.UserModel;
import kg.hackaton.project.services.UserRoleService;
import kg.hackaton.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    private OblastService oblastService;

    @Autowired
    private RayonService rayonService;

    @Autowired
    private AppartmentService appartmentService;

    @Autowired
    private ManufacturerService manufacturerService;

    private User currentUser;

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

    // OBLAST
    @PreAuthorize("isAuthenticated() and hasPermission('OBLAST_READ', 'SUPER_ADMIN')")
    @GetMapping("/oblast/list")
    public String getOblastList(Model model) {
        model.addAttribute("title", "Список областей");
        model.addAttribute("oblasts", oblastService.findAll());
        return "oblasts/oblast_list";
    }

    //RAYON
    @PreAuthorize("isAuthenticated() and hasPermission('RAYON_READ', 'SUPER_ADMIN')")
    @GetMapping("/rayon/list")
    public String getrayonList(Model model){
        model.addAttribute("title", "Список районов");
        model.addAttribute("rayons", rayonService.findAll());
        return "rayons/rayon_list";
    }

    //MANUFACTURER
    @PreAuthorize("isAuthenticated() and hasPermission('MANUFACTURER_READ', 'SUPER_ADMIN')")
    @GetMapping("/manufacturer/list")
    public String getManufacturerList(Model model){
        setUserCredentials(model);
        model.addAttribute("title", "Список жилых комплексов");
        model.addAttribute("manufacturers", manufacturerService.findAll());
        return "manufacturers/manufacturer_list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('MANUFACTURERUPDATE', 'SUPER_ADMIN')")
    @GetMapping("/manufacturer/edit/{id}")
    public String getManufacturerEditForm(@PathVariable("id") Long id, Model model){
        setUserCredentials(model);
        model.addAttribute("title", "Изменение жилого комплекса");
        return "manufacturers/add_or_update_form";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('MANUFACTURER_UPDATE', 'SUPER_ADMIN')")
    @PostMapping("/manufacturer/edit")
    public String setManufacturer(@Validated @ModelAttribute("manufacturer") ManufacturerModel manufacturerModel) {
        manufacturerService.update(manufacturerModel);
        return "redirect:/admin/manufacturer/list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('MANUFACTURER_CREATE', 'SUPER_ADMIN')")
    @GetMapping("/manufacturer/add")
    public String addNewManufacturer(Model model) {
        setUserCredentials(model);
        model.addAttribute("title", "Добавление жилого комплекса");
        return "manufacturers/add_or_update_form";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('MANUFACTURER_CREATE', 'SUPER_ADMIN')")
    @PostMapping("/manufacturer/add")
    public String createManufacturer(@Validated @ModelAttribute("rayon") ManufacturerModel manufacturerModel) {
        manufacturerService.create(manufacturerModel);
        return "redirect:/admin/manufacturer/list";
    }

    public void setUserCredentials(Model model){
        getCurrentUser();
        Long id = currentUser.getId();
        model.addAttribute("name", userService.getById(id) != null ? userService.getById(id).getName() : "Имя");
        model.addAttribute("surname", userService.getById(id) != null ? userService.getById(id).getSurname() : "Фамилия");
    }

    private void getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        currentUser = userService.getUserByUsername(userDetails.getUsername());
    }

//    @PreAuthorize("isAuthenticated() and hasPermission('USER_CREATE', 'SUPER_ADMIN')")
//    @PostMapping(value = "/user/add")
//    public String addEmployee(@ModelAttribute("UserModel") UserModel userModel) {
//        employeeService.create(employeeModel);
//        return "redirect:/admin/user/list";
//    }
}
