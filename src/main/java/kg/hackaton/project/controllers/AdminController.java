package kg.hackaton.project.controllers;

import kg.hackaton.project.entities.User;
import kg.hackaton.project.entities.User;
import kg.hackaton.project.models.ClientModel;
import kg.hackaton.project.models.ManufacturerModel;
import kg.hackaton.project.models.SerieModel;
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
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ClientService clientService;

    @Autowired
    private SerieService serieService;

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
        return "users/user_form";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('USER_CREATE', 'SUPER_ADMIN')")
    @PostMapping(value = "/user/add")
    public String addEmployee(@ModelAttribute("UserModel") UserModel userModel) {
        userService.create(userModel);
        return "redirect:/admin/user/list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('USER_READ', 'SUPER_ADMIN')")
    @GetMapping("/user/{id}")
    public String getUserEditForm(@PathVariable("id") Long userId, Model model){
        User user = userService.getById(userId);
        model.addAttribute("title", "Редактирование пользователя");
        model.addAttribute("roles", userRoleService.getAllUserRoles());
        model.addAttribute("user", user);
        return "users/user_form";
    }

    // OBLAST
    @PreAuthorize("isAuthenticated() and hasPermission('OBLAST_READ', 'SUPER_ADMIN')")
    @GetMapping("/oblast/list")
    public String getOblastList(Model model) {
        model.addAttribute("title", "Список областей");
        model.addAttribute("oblasts", oblastService.findAll());
        return "oblasts/oblast_list";
    }

    @PostMapping(value = "/user/changeStatus/{id}")
    public String changeUserStatus(@PathVariable("id") Long userId) {
        userService.changeUserStatus(userId);
        return "redirect:/admin/user/list";
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

    //CLIENTS
    @PreAuthorize("isAuthenticated() and hasPermission('CLIENT_READ', 'SUPER_ADMIN')")
    @GetMapping("/client/list")
    public String getClientsList(Model model) {
        model.addAttribute("clients", clientService.findAll());
        getCurrentUser();
        model.addAttribute("username", currentUser.getName());
        model.addAttribute("surname", currentUser.getSurname());
        return "clients/client_list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('CLIENT_UPDATE', 'SUPER_ADMIN')")
    @GetMapping("/client/{id}")
    public String clientEditForm(@PathVariable("id") Long clientId, Model model) {
        model.addAttribute("client", clientService.getById(clientId));
        getCurrentUser();
        model.addAttribute("username", currentUser.getName());
        model.addAttribute("surname", currentUser.getSurname());
        return "clients/client_form";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('CLIENT_CREATE', 'SUPER_ADMIN')")
    @PostMapping(value = "/worldorgs/add")
    public String addClient(@Validated @ModelAttribute("client") ClientModel clientModel) {
        clientService.create(clientModel);
        return "redirect:/admin/client/list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('CLIENT_UPDATE', 'SUPER_ADMIN')")
    @PostMapping(value = "/client/update")
    public String updateClient(@Validated @ModelAttribute("client") ClientModel clientModel) {
        clientService.update(clientModel);
        return "redirect:/admin/client/list";
    }

    //SERIES
    @PreAuthorize("isAuthenticated() and hasPermission('SERIE_READ', 'SUPER_ADMIN')")
    @GetMapping("/serie/list")
    public String getSerieList(Model model) {
        model.addAttribute("series", serieService.findAll());
        getCurrentUser();
        model.addAttribute("username", currentUser.getName());
        model.addAttribute("surname", currentUser.getSurname());
        return "series/serie_list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('SERIE_UPDATE', 'SUPER_ADMIN')")
    @GetMapping("/serie/{id}")
    public String serieEditForm(@PathVariable("id") Long serieId, Model model) {
        model.addAttribute("serie", serieService.getById(serieId));
        getCurrentUser();
        model.addAttribute("username", currentUser.getName());
        model.addAttribute("surname", currentUser.getSurname());
        return "series/serie_form";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('SERIE_CREATE', 'SUPER_ADMIN')")
    @PostMapping(value = "/serie/add")
    public String addSerie(@Validated @ModelAttribute("serie") SerieModel serieModel) {
        serieService.create(serieModel);
        return "redirect:/admin/serie/list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('SERIE_UPDATE', 'SUPER_ADMIN')")
    @PostMapping(value = "/serie/update")
    public String updateSerie(@Validated @ModelAttribute("serie") SerieModel serieModel) {
        serieService.update(serieModel);
        return "redirect:/admin/serie/list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('SERIE_UPDATE', 'SUPER_ADMIN')")
    @GetMapping(value = "/serie/delete/{id}")
    public String deleteSerie(@PathVariable("id") Long id) {
        serieService.delete(id);
        return "redirect:/admin/serie/list";
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
    @PreAuthorize("isAuthenticated() and hasPermission('USER_UPDATE', 'SUPER_ADMIN')")
    @PostMapping(value = "/user/update")
    public String editUser(@ModelAttribute("user") UserModel userModel) {
        System.out.println(userModel.getUserRoleId());
        userService.update(userModel);
        return "redirect:/admin/user/list";
    }
}
