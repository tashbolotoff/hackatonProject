package kg.hackaton.project.controllers;

import kg.hackaton.project.entities.User;
import kg.hackaton.project.enums.BusyOrFreeStatus;
import kg.hackaton.project.enums.Condition;
import kg.hackaton.project.enums.TypeOfHouse;
import kg.hackaton.project.enums.TypeOfSale;
import kg.hackaton.project.models.AppartmentModel;
import kg.hackaton.project.models.ClientModel;
import kg.hackaton.project.models.ManufacturerModel;
import kg.hackaton.project.models.SerieModel;
import kg.hackaton.project.entities.Permission;
import kg.hackaton.project.entities.PermissionCategory;
import kg.hackaton.project.models.*;
import kg.hackaton.project.repositories.PermissionCategoryRepo;
import kg.hackaton.project.services.*;
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

import java.util.ArrayList;

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
    private PermissionService permissionService;

    @Autowired
    private PermissionCategoryRepo permissionCategoryRepo;

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
        setUserCredentials(model);
        return "users/user_list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('USER_CREATE', 'SUPER_ADMIN')")
    @GetMapping("/user/add")
    public String getUserAddForm(Model model) {
        model.addAttribute("title", "Добавление нового пользователя");
        model.addAttribute("roles", userRoleService.getAllUserRoles());
        setUserCredentials(model);
        return "users/user_form";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('USER_CREATE', 'SUPER_ADMIN')")
    @PostMapping(value = "/user/add")
    public String addUser(@ModelAttribute("UserModel") UserModel userModel) {
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
        setUserCredentials(model);
        return "users/user_form";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('USER_UPDATE', 'SUPER_ADMIN')")
    @PostMapping(value = "/user/update")
    public String editUser(@ModelAttribute("user") UserModel userModel) {
        System.out.println(userModel.getUserRoleId());
        userService.update(userModel);
        return "redirect:/admin/user/list";
    }

    // OBLAST
    @PreAuthorize("isAuthenticated() and hasPermission('OBLAST_READ', 'SUPER_ADMIN')")
    @GetMapping("/oblast/list")
    public String getOblastList(Model model) {
        model.addAttribute("title", "Список областей");
        model.addAttribute("oblasts", oblastService.findAll());
        setUserCredentials(model);
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
        setUserCredentials(model);
        return "rayons/rayon_list";
    }

    //MANUFACTURER
    @PreAuthorize("isAuthenticated() and hasPermission('MANUFACTURER_READ', 'SUPER_ADMIN')")
    @GetMapping("/manufacturer/list")
    public String getManufacturerList(Model model) {
        model.addAttribute("title", "Список жилых комплексов");
        model.addAttribute("manufacturers", manufacturerService.findAll());
        setUserCredentials(model);
        return "manufacturers/manufacturer_list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('MANUFACTURER_UPDATE', 'SUPER_ADMIN')")
    @GetMapping("/manufacturer/edit/{id}")
    public String getManufacturerEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("title", "Изменение жилого комплекса");
        model.addAttribute("manufacturer", manufacturerService.getById(id));
        setUserCredentials(model);
        return "manufacturers/add_or_update_form";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('MANUFACTURER_UPDATE', 'SUPER_ADMIN')")
    @PostMapping("/manufacturer/update")
    public String setManufacturer(@Validated @ModelAttribute("manufacturer") ManufacturerModel manufacturerModel) {
        manufacturerService.update(manufacturerModel);
        return "redirect:/admin/manufacturer/list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('MANUFACTURER_CREATE', 'SUPER_ADMIN')")
    @GetMapping("/manufacturer/add")
    public String addNewManufacturer(Model model) {
        model.addAttribute("title", "Добавление жилого комплекса");
        setUserCredentials(model);
        return "manufacturers/add_or_update_form";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('MANUFACTURER_CREATE', 'SUPER_ADMIN')")
    @PostMapping("/manufacturer/add")
    public String createManufacturer(@Validated @ModelAttribute("manufacturer") ManufacturerModel manufacturerModel) {
        manufacturerService.create(manufacturerModel);
        return "redirect:/admin/manufacturer/list";
    }

    //APPARTMENT
    @PreAuthorize("isAuthenticated() and hasPermission('APPARTMENT_READ', 'SUPER_ADMIN')")
    @GetMapping("/appartment/list")
    public String getAppartmentList(Model model) {
        model.addAttribute("title", "Список недвижимости");
        model.addAttribute("appartments", appartmentService.findAll());
        setUserCredentials(model);
        return "appartments/appartment_list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('APPARTMENT_READ', 'SUPER_ADMIN')")
    @GetMapping("/appartment/listImg")
    public String getAppartmentListWithImg(Model model) {
        model.addAttribute("title", "Список недвижимости");
        model.addAttribute("appartments", appartmentService.findAll());
        setUserCredentials(model);
        return "index";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('APPARTMENT_UPDATE', 'SUPER_ADMIN')")
    @GetMapping("/appartment/edit/{id}")
    public String getAppartmentEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("title", "Изменение недвижимости");
        model.addAttribute("appartment", appartmentService.getById(id));
        model.addAttribute("rayons", rayonService.findAll());
        model.addAttribute("manufacturers", manufacturerService.findAll());
        model.addAttribute("series", serieService.findAll());
        model.addAttribute("conditions", Condition.values());
        model.addAttribute("typesOfHouse", TypeOfHouse.values());
        model.addAttribute("typesOfSale", TypeOfSale.values());
        model.addAttribute("busysOrFrees", BusyOrFreeStatus.values());
        setUserCredentials(model);
        return "appartments/add_or_update_form";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('APPARTMENT_UPDATE', 'SUPER_ADMIN')")
    @PostMapping("/appartment/update")
    public String setAppartment(@Validated @ModelAttribute("appartment") AppartmentModel appartmentModel) {
        appartmentService.update(appartmentModel);
        return "redirect:/admin/appartment/list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('APPARTMENT_CREATE', 'SUPER_ADMIN')")
    @GetMapping("/appartment/add")
    public String addNewAppartment(Model model) {
        model.addAttribute("title", "Добавление квартиры/дома");
        model.addAttribute("rayons", rayonService.findAll());
        model.addAttribute("manufacturers", manufacturerService.findAll());
        model.addAttribute("series", serieService.findAll());
        model.addAttribute("conditions", Condition.values());
        model.addAttribute("typesOfHouse", TypeOfHouse.values());
        model.addAttribute("typesOfSale", TypeOfSale.values());
        model.addAttribute("busysOrFrees", BusyOrFreeStatus.values());
        setUserCredentials(model);
        return "appartments/add_or_update_form";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('APPARTMENT_CREATE', 'SUPER_ADMIN')")
    @PostMapping("/appartment/add")
    public String createAppartment(@Validated @ModelAttribute("appartment") AppartmentModel appartmentModel) {
        appartmentService.create(appartmentModel);
        return "redirect:/admin/appartment/list";
    }

    //CLIENTS
    @PreAuthorize("isAuthenticated() and hasPermission('CLIENT_READ', 'SUPER_ADMIN')")
    @GetMapping("/client/list")
    public String getClientsList(Model model) {
        setUserCredentials(model);
        model.addAttribute("clients", clientService.findAll());
        setUserCredentials(model);
        return "clients/client_list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('CLIENT_UPDATE', 'SUPER_ADMIN')")
    @GetMapping("/client/{id}")
    public String clientEditForm(@PathVariable("id") Long clientId, Model model) {
        setUserCredentials(model);
        model.addAttribute("client", clientService.getById(clientId));
        setUserCredentials(model);
        return "clients/client_form";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('CLIENT_UPDATE', 'SUPER_ADMIN')")
    @PostMapping(value = "/client/update")
    public String updateClient(@Validated @ModelAttribute("client") ClientModel clientModel) {
        clientService.update(clientModel);
        return "redirect:/admin/client/list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('CLIENT_CREATE', 'SUPER_ADMIN')")
    @GetMapping("/client/add")
    public String getClientAddForm(Model model) {
        model.addAttribute("title", "Добавление нового клиента");
        setUserCredentials(model);
        return "clients/client_form";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('CLIENT_CREATE', 'SUPER_ADMIN')")
    @PostMapping(value = "/client/add")
    public String addClient(@Validated @ModelAttribute("client") ClientModel clientModel) {
        clientService.create(clientModel);
        return "redirect:/admin/client/list";
    }

    //SERIES
    @PreAuthorize("isAuthenticated() and hasPermission('SERIE_READ', 'SUPER_ADMIN')")
    @GetMapping("/serie/list")
    public String getSerieList(Model model) {
        setUserCredentials(model);
        model.addAttribute("series", serieService.findAll());
        setUserCredentials(model);
        return "series/serie_list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('SERIE_UPDATE', 'SUPER_ADMIN')")
    @GetMapping("/serie/{id}")
    public String serieEditForm(@PathVariable("id") Long serieId, Model model) {
        setUserCredentials(model);
        model.addAttribute("serie", serieService.getById(serieId));
        setUserCredentials(model);
        return "series/serie_form";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('SERIE_UPDATE', 'SUPER_ADMIN')")
    @PostMapping(value = "/serie/update")
    public String updateSerie(@Validated @ModelAttribute("serie") SerieModel serieModel) {
        serieService.update(serieModel);
        return "redirect:/admin/serie/list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('SERIE_CREATE', 'SUPER_ADMIN')")
    @GetMapping("/serie/add")
    public String getSerieAddForm(Model model) {
        model.addAttribute("title", "Добавление новой серии");
        setUserCredentials(model);
        return "series/serie_form";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('SERIE_CREATE', 'SUPER_ADMIN')")
    @PostMapping(value = "/serie/add")
    public String addSerie(@Validated @ModelAttribute("serie") SerieModel serieModel) {
        serieService.create(serieModel);
        return "redirect:/admin/serie/list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('SERIE_UPDATE', 'SUPER_ADMIN')")
    @GetMapping(value = "/serie/delete/{id}")
    public String deleteSerie(@PathVariable("id") Long id) {
        serieService.delete(id);
        return "redirect:/admin/serie/list";
    }


    @PreAuthorize("isAuthenticated() and hasPermission('SUPER_ADMIN')")
    @PostMapping("/userRole/permission/add")
    public String updatePermissions(@ModelAttribute("PermissionModel") PermissionModel permissionModel){
        permissionService.grantSelectedPrivileges(permissionModel);
        return "redirect:/admin/userRole/permissions";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('SUPER_ADMIN')")
    @GetMapping("/userRole/permissions")
    public String getPermissionList(Model model){
        setUserCredentials(model);
        model.addAttribute("roles", userRoleService.getAllUserRoles());
        ArrayList<PermissionBoolModel> permissionBoolModels = new ArrayList<>();
        for(PermissionCategory permissionCategory : permissionCategoryRepo.findAll()){
            for(Permission permission : permissionCategory.getPermissions()) {
                PermissionBoolModel permissionBoolModel = new PermissionBoolModel();
                permissionBoolModel.setPermissionBool(false);
                permissionBoolModel.setPermId(permission.getId());
                permissionBoolModels.add(permissionBoolModel);
            }
        }
        PermissionModel permissionModel = new PermissionModel();
        permissionModel.setPermissionBools(permissionBoolModels);
        model.addAttribute("permissionModel", permissionModel);
        model.addAttribute("counter", new Counter());
        model.addAttribute("permissionCategories", permissionCategoryRepo.findAll());
        setUserCredentials(model);
        return "permissions/permissionEditList";
    }

    @GetMapping("/charts")
    public String getChart(Model model) {
        model.addAttribute("list", appartmentService.findAll());
        setUserCredentials(model);
        return "charts/charts";
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
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        currentUser = userService.getUserByUsername(userDetails.getUsername());
    }


}
