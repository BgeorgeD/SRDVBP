package com.example.vacationrecommender.controller;


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
    public RedirectView submitFeedback(
            @RequestParam("destinationId") Long destinationId,
            @RequestParam("text") String text,
            @RequestParam("stars") int stars,
            @RequestParam("user") String user,
            RedirectAttributes redirectAttributes) {
        System.out.println("Destination ID: " + destinationId);
        System.out.println("Text: " + text);
        System.out.println("Stars: " + stars);
        System.out.println("User: " + user);

        try {
            // Salvează comentariul
            commentService.addComment(destinationId, text, user);

            // Salvează rating-ul
            ratingService.addRating(destinationId, stars);

            // Mesaj de succes
            redirectAttributes.addFlashAttribute("successMessage", "Feedback-ul a fost trimis cu succes!");
        } catch (Exception e) {
            // Mesaj de eroare
            redirectAttributes.addFlashAttribute("errorMessage", "A apărut o eroare la trimiterea feedback-ului.");
        }

        // Redirecționează către "/mountain"
        return new RedirectView("/mountain");
    }
}

