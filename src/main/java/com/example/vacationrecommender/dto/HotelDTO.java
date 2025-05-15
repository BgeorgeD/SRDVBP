package com.example.vacationrecommender.dto;


public class HotelDTO {
    private String id;
    private String fullName;
    private String label;
    private String locationName;
    private Location location;

    public HotelDTO() {}

    // Getteri și setteri
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
}

