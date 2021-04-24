package kg.hackaton.project.controllers;

import kg.hackaton.project.entities.Appartment;
import kg.hackaton.project.repositories.AppartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/map")
public class MapController {
    @Autowired
    private AppartmentRepo appartmentRepo;

    @PreAuthorize("isAuthenticated() and hasPermission('MAP_READ', 'SUPER_ADMIN')")
    @GetMapping("/show")
    public String showMap(Model model) {
        model.addAttribute("title", "Карта");
        List<Appartment> appartments = appartmentRepo.findAll();
        for(Appartment appartment : appartments){
            appartment.setImgPaths(null);
        }
        model.addAttribute("list", appartments);
        return "map";
    }
}
