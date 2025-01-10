package com.example.vacationrecommender.entity;

import jakarta.persistence.*;

@Entity
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String activities;
    private Double budget;
    private String climate;
    private String image; // URL sau cale pentru imagine
    private String video; // URL sau cale pentru video

    public Destination() {
    }

    // Getters și Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getActivities() { return activities; }
    public void setActivities(String activities) { this.activities = activities; }
    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }
    public String getClimate() { return climate; }
    public void setClimate(String climate) { this.climate = climate; }
}
