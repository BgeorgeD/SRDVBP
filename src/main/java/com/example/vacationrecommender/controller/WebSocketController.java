package com.example.vacationrecommender.controller;

import com.example.vacationrecommender.entity.Comment;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/comment") // Prefix definit în WebSocketConfig
    @SendTo("/topic/notifications") // Topic-ul unde vor fi trimise mesajele
    public String notifyNewComment(Comment comment) {
        return "Nou comentariu adăugat de: " + comment.getUsername();
    }
}
