package com.example.vacationrecommender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    // Ruta pentru pagina de login
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Returnează numele fișierului HTML pentru login (ex: login.html)
    }

    // Ruta pentru pagina principală
    @GetMapping("/index")
    public String homePage(Model model) {
        model.addAttribute("message", "Bun venit la Vacation Recommender!");
        return "index"; // Returnează numele fișierului HTML pentru pagina principală (ex: index.html)
    }


}
