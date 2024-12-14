package com.example.vacationrecommender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String showLoginPage() {
        System.out.println("Accessing login page..."); // Log pentru debug
        return "login";
    }
}


