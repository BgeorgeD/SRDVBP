package com.example.vacationrecommender.controller;


import com.example.vacationrecommender.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    // Adaugă un rating
    @PostMapping("/add")
    public String addRating(@RequestParam Long destinationId,
                            @RequestParam int stars) {
        ratingService.addRating(destinationId, stars);
        return "redirect:/mountain"; // Schimbă URL-ul în cel al paginii tale
    }

    // Afișează rating-ul mediu pentru o destinație
    @GetMapping("/{destinationId}/average")
    public String getAverageRating(@PathVariable Long destinationId, Model model) {
        double averageRating = ratingService.calculateAverageRating(destinationId);
        model.addAttribute("averageRating", averageRating);
        return "rating"; // Pagina pentru afișarea rating-ului mediu
    }
}

