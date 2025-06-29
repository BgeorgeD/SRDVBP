package com.example.vacationrecommender.controller;

import com.example.vacationrecommender.dto.VacationDTO;
import org.jsoup.Jsoup;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InspirationController {

    @GetMapping("/inspiration")
    public String showInspirationPage() {
        return "inspiration"; // pagina HTML care va încărca din Flask
    }



}
