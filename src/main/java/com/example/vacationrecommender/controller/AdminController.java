/*package com.example.vacationrecommender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @GetMapping
    public String admin_Dashboard() {
        return "admin_dashboard"; // Afișează dashboard-ul adminului
    }

    @GetMapping("/destinations")
    public String manageDestinations() {
        return "admin_destinations"; // Pagină pentru adăugare/modificare destinații
    }
}*/

package com.example.vacationrecommender.controller;

import com.example.vacationrecommender.entity.Destination;
import com.example.vacationrecommender.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String admin_Dashboard() {
        return "admin_dashboard"; // Afișează dashboard-ul adminului
    }


    private final DestinationService destinationService;

    @Autowired
    public AdminController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }



    // 1. Afișează pagina de gestionare a destinațiilor
    @GetMapping("/destinations")
    public String showDestinations(Model model) {
        model.addAttribute("destinations", destinationService.findAllDestinations());
        model.addAttribute("destination", new Destination()); // Obiect gol pentru formular
        return "admin_destinations";
    }


    // 2. Adaugă o nouă destinație


    @PostMapping("/destinations/add")
    public String addDestination(@ModelAttribute Destination destination) {
        destinationService.saveDestination(destination);
        return "redirect:/admin/destinations";
    }


    // 3. Șterge o destinație după ID
    @PostMapping("/destinations/delete/{id}")
    public String deleteDestination(@PathVariable Long id) {
        destinationService.deleteDestinationById(id);
        return "redirect:/admin/destinations";
    }
}


