package com.example.vacationrecommender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String showHomePage() {
        System.out.println("Accessing home page..."); // Log pentru debug
        return "home";
    }
    @GetMapping("/mountain")
    public String showMountainPage() {
        return "home_subdivisions/mountain";
    }

    @GetMapping("/sea")
    public String showSeaPage() {
        return "home_subdivisions/sea";
    }

    @GetMapping("/city")
    public String showCityPage() {
        return "home_subdivisions/city";
    }

    @GetMapping("/jungle")
    public String showJunglePage() {
        return "home_subdivisions/jungle";
    }
}

