package com.example.vacationrecommender.controller;


import com.example.vacationrecommender.entity.Comment;
import com.example.vacationrecommender.entity.Rating;
import com.example.vacationrecommender.service.CommentService;
import com.example.vacationrecommender.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final CommentService commentService;
    private final RatingService ratingService;

    @Autowired
    public TestController(CommentService commentService, RatingService ratingService) {
        this.commentService = commentService;
        this.ratingService = ratingService;
    }

    // Endpoint pentru a adăuga un comentariu
    @PostMapping("/addComment")
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.saveComment(comment);
    }

    // Endpoint pentru a adăuga un rating
    @PostMapping("/addRating")
    public Rating addRating(@RequestBody Rating rating) {
        return ratingService.saveRating(rating);
    }

    // Endpoint pentru a afișa toate comentariile
    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        return commentService.findAllComments();
    }

    // Endpoint pentru a afișa toate rating-urile
    @GetMapping("/ratings")
    public List<Rating> getAllRatings() {
        return ratingService.findAllRatings();
    }
}

