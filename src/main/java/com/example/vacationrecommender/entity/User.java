package com.example.vacationrecommender.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // Numele tabelului în MySQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment în MySQL
    private Long id;

    @Column(nullable = false, unique = true) // Username unic
    private String username;

    @Column(nullable = false) // Parola este obligatorie
    private String password;

    @Column(nullable = false) // Rolul este obligatoriu
    private String role;

    // Getteri și Setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
