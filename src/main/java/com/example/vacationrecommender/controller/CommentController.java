package com.example.vacationrecommender.controller;


import com.example.vacationrecommender.entity.Comment;
import com.example.vacationrecommender.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Adaugă un comentariu
    @PostMapping("/add")
    public String addComment(@RequestParam Long destinationId,
                             @RequestParam String text,
                             @RequestParam String user) {
        commentService.addComment(destinationId, text, user);
        return "redirect:/destination/" + destinationId; // Redirecționează la pagina destinației
    }

    // Afișează toate comentariile pentru o destinație
    @GetMapping("/{destinationId}")
    public String getComments(@PathVariable Long destinationId, Model model) {
        model.addAttribute("comments", commentService.getCommentsByDestination(destinationId));
        return "comments"; // Pagina pentru afișarea comentariilor
    }
}

