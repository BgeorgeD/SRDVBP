package com.example.vacationrecommender.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "top_vacation_feedback")
public class TopVacationFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nume_pachet")
    private String numePachet;

    @Column(name = "oras_start")
    private String orasStart;

    @Column(name = "comentariu")
    private String comentariu;

    @Column(name = "rating")
    private int rating;

    @Column(name = "username")
    private String username;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumePachet() { return numePachet; }
    public void setNumePachet(String numePachet) { this.numePachet = numePachet; }

    public String getOrasStart() { return orasStart; }
    public void setOrasStart(String orasStart) { this.orasStart = orasStart; }

    public String getComentariu() { return comentariu; }
    public void setComentariu(String comentariu) { this.comentariu = comentariu; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
