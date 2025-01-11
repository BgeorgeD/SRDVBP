package com.example.vacationrecommender.service;

import com.example.vacationrecommender.entity.Comment;
import com.example.vacationrecommender.entity.Destination;
import com.example.vacationrecommender.repository.CommentRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.time.ZoneId;
import java.util.Date;
import java.time.format.DateTimeFormatter;



import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final DestinationService destinationService;
    private final SimpMessagingTemplate messagingTemplate;

    public CommentService(CommentRepository commentRepository,
                          DestinationService destinationService,
                          SimpMessagingTemplate messagingTemplate) {
        this.commentRepository = commentRepository;
        this.destinationService = destinationService;
        this.messagingTemplate = messagingTemplate;
    }



    public void addComment(Long destinationId, String text, String username) {
        Comment comment = new Comment();
        comment.setText(text);
        comment.setUsername(username);
        comment.setDate(LocalDateTime.now());

        // Găsește destinația din baza de date
        Destination destination = destinationService.getById(destinationId);

        comment.setDestination(destination);
        commentRepository.save(comment);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        comment.setFormattedDate(LocalDateTime.now().format(formatter));
        // Trimite notificarea prin WebSocket
        messagingTemplate.convertAndSend("/topic/notifications", "Comentariu nou de la " + username);

        // Trimitere notificare cu detalii
        Map<String, String> notificationData = new HashMap<>();
        notificationData.put("username", username);
        notificationData.put("text", text);
        notificationData.put("date", LocalDateTime.now().toString());

        messagingTemplate.convertAndSend("/topic/notifications", notificationData);

    }
    // Metodă pentru a returna toate comentariile
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Metodă pentru a șterge un comentariu după ID
    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }


    public List<Comment> getCommentsByDestination(Long destinationId) {
        return commentRepository.findByDestinationId(destinationId);}

}




