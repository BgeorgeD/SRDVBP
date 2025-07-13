package com.example.vacationrecommender.controller;

import com.example.vacationrecommender.service.AiService;
import com.example.vacationrecommender.service.VacationRecommendation;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/*@Controller
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

    @GetMapping("/ai-vacation/{id}")
    public String showVacationDetails(@PathVariable int id, Model model) {
        if (id < 0 || id >= cache.size()) return "redirect:/api/ai";
        model.addAttribute("recomandare", cache.get(id));
        return "ai-vacation-details";
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


}*/

@Controller
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;
    private List<VacationRecommendation> cache = new ArrayList<>();

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping
    public String showRecommendationForm() {
        return "recommendation";
    }

    @GetMapping("/rezerva/{id}")
    public String showReservationForm(@PathVariable int id, Model model) {
        if (id < 0 || id >= cache.size()) return "redirect:/api/ai";
        VacationRecommendation recomandare = cache.get(id);
        model.addAttribute("recomandare", recomandare);
        return "rezervare";
    }



    @PostMapping("/rezerva/{id}")
    public String confirmReservation(@PathVariable int id, @RequestParam Map<String, String> formData) {
        // Aici poți procesa formularul dacă vrei, dar pentru acum nu e necesar.

        return "redirect:/api/ai"; // Te întorci direct la pagina anterioară
    }



    @PostMapping("/recommend")
    public String handleRecommendation(@RequestParam("text") String text, Model model) {
        try {
            List<VacationRecommendation> recomandari = aiService.getRecommendations(text);
            model.addAttribute("recomandari", recomandari);
            cache = recomandari; // ✅ salvăm lista
        } catch (Exception e) {
            model.addAttribute("recomandari", List.of());
        }
        return "recommendation";
    }
}

