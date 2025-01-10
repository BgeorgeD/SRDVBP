package com.example.vacationrecommender.service;

import com.example.vacationrecommender.entity.Comment;
import com.example.vacationrecommender.entity.Destination;
import com.example.vacationrecommender.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // Adaugă un comentariu legat de o destinație
    public void addComment(Long destinationId, String text, String user) {
        Comment comment = new Comment();
        Destination destination = new Destination();
        destination.setId(destinationId); // Setează ID-ul destinației

        comment.setDestination(destination);
        comment.setText(text);
        comment.setUser(user);

        commentRepository.save(comment);
    }

    // Găsește toate comentariile pentru o destinație
    public List<Comment> getCommentsByDestination(Long destinationId) {
        return commentRepository.findByDestinationId(destinationId);
    }

    // Salvează un comentariu
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Găsește toate comentariile
    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }
}


