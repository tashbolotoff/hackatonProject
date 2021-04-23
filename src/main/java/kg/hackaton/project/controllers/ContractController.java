package kg.hackaton.project.controllers;

import kg.hackaton.project.enums.TypeOfSale;
import kg.hackaton.project.models.AppartmentClientModel;
import kg.hackaton.project.repositories.AppartmentClientRepo;
import kg.hackaton.project.repositories.AppartmentRepo;
import kg.hackaton.project.repositories.ClientRepo;
import kg.hackaton.project.services.AppartmentClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private AppartmentClientRepo appartmentClientRepo;

    @Autowired
    private AppartmentClientService appartmentClientService;

    @Autowired
    private AppartmentRepo appartmentRepo;

    @Autowired
    private ClientRepo clientRepo;

    @PreAuthorize("isAuthenticated() and hasPermission('CONTRACT_READ', 'SUPER_ADMIN')")
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("title", "Cписок договоров");
        model.addAttribute("list", appartmentClientRepo.findAll());
        return "contract/contract_list";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('CONTRACT_CREATE', 'SUPER_ADMIN')")
    @GetMapping("/add/{appartmentId}")
    public String add(@PathVariable("appartmentId") Long appartmentId, Model model) {
        model.addAttribute("title", "Добавление договора");
        model.addAttribute("appartment", appartmentRepo.getOne(appartmentId));
        model.addAttribute("clients", clientRepo.findAll());
        model.addAttribute("status", TypeOfSale.values());
        return "contract/contract_form";
    }

    @PreAuthorize("isAuthenticated() and hasPermission('CONTRACT_CREATE', 'SUPER_ADMIN')")
    @PostMapping("/add")
    public String addA(@ModelAttribute("AppartmentClientModel") AppartmentClientModel appartmentClientModel) {
        appartmentClientService.create(appartmentClientModel);
        return "redirect:/contract/list";
    }
}
