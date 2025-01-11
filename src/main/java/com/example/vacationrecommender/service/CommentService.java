package com.example.vacationrecommender.service;

import com.example.vacationrecommender.entity.Comment;
import com.example.vacationrecommender.entity.Destination;
import com.example.vacationrecommender.repository.CommentRepository;
import org.springframework.stereotype.Service;
import java.time.ZoneId;
import java.util.Date;
import java.time.format.DateTimeFormatter;



import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final DestinationService destinationService;

    public CommentService(CommentRepository commentRepository, DestinationService destinationService) {
        this.commentRepository = commentRepository;
        this.destinationService = destinationService;
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




