package com.example.vacationrecommender.dto;



public record GlobDTO(
        String id,
        String name,
        String description,
        String imageUrl,
        double distanceMeters,
        double lat,
        double lng
) {}

