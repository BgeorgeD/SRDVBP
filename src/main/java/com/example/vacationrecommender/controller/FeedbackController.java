package com.example.vacationrecommender.controller;


import com.example.vacationrecommender.entity.Destination;
import com.example.vacationrecommender.service.CommentService;
import com.example.vacationrecommender.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class FeedbackController {

    private final CommentService commentService;
    private final RatingService ratingService;

    @Autowired
    public FeedbackController(CommentService commentService, RatingService ratingService) {
        this.commentService = commentService;
        this.ratingService = ratingService;
    }

    @PostMapping("/submitFeedback")
    public String submitFeedback(@RequestParam("destinationId") Long destinationId,
                                 @RequestParam("text") String text,
                                 @RequestParam("stars") int stars,
                                 @RequestParam("user") String username) {
        // Salvează comentariul
        commentService.addComment(destinationId, text, username);

        // Salvează rating-ul
        ratingService.addRating(destinationId, stars);

        // Redirecționează către pagina potrivită
        if (destinationId == 1) {
            return "redirect:/mountain";
        } else if (destinationId == 2) {
            return "redirect:/sea";
        } else if (destinationId == 3) {
            return "redirect:/city";
        } else if (destinationId == 4) {
            return "redirect:/jungle";
        } else {
            throw new IllegalArgumentException("Destinația cu ID-ul " + destinationId + " nu există.");
        }
    }



}

