package com.example.vacationrecommender.entity;

import jakarta.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    private int stars;

    public void setDestination(Destination destination) {
    }

    public void setStars(int stars) {
    }

    public int getStars() {
        return 0;
    };


    // Getters și Setters
}

