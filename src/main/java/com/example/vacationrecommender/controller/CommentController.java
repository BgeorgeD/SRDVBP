package com.example.vacationrecommender.controller;

import com.example.vacationrecommender.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public String addComment(@RequestParam("destinationId") Long destinationId,
                             @RequestParam("text") String text,
                             @RequestParam("username") String username) {
        commentService.addComment(destinationId, text, username);
        return "redirect:/mountain";
    }

}

