package com.example.vacationrecommender.controller;

import com.example.vacationrecommender.service.AiService;
import com.example.vacationrecommender.service.VacationRecommendation;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Controller
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping
    public String showRecommendationForm() {
        return "recommendation";
    }

    @PostMapping("/recommend")
    public String handleRecommendation(@RequestParam("text") String text, Model model) {
        try {
            List<VacationRecommendation> recomandari = aiService.getRecommendations(text);
            model.addAttribute("recomandari", recomandari);
        } catch (Exception e) {
            model.addAttribute("recomandari", List.of());
        }
        return "recommendation";
    }
}
