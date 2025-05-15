package com.example.vacationrecommender.dto;

import java.util.List;

public class Results {
    private List<HotelDTO> hotels;

    public List<HotelDTO> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelDTO> hotels) {
        this.hotels = hotels;
    }
}
