package com.example.vacationrecommender.dto;


public record GlobDTO(
        String xid,
        String name,
        String description,
        String imageUrl,
        double distanceMeters,
        double lat,
        double lng
) {}



