package com.example.vacationrecommender.controller;


import com.example.vacationrecommender.entity.User;
import com.example.vacationrecommender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Ruta pentru pagina de login
    @GetMapping("/login")
    public String showloginPage() {
        System.out.println("Accessing login page..."); // Log pentru debug
        return "login";
    }

}



