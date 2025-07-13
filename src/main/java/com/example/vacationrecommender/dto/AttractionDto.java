package com.example.vacationrecommender.dto;

public class AttractionDto {
    private String name;
    private double lat;
    private double lon;
    private String kinds;
    private String xid;


    // Constructori
    public AttractionDto(String name, double lat, double lon, String kinds, String xid) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.kinds = kinds;
        this.xid = xid;
    }

    public AttractionDto() {}

    // Getteri și Setteri
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }

    public double getLon() { return lon; }
    public void setLon(double lon) { this.lon = lon; }

    public String getKinds() { return kinds; }
    public void setKinds(String kinds) { this.kinds = kinds; }

    public String getXid() { return xid; } // ✅ getter
    public void setXid(String xid) { this.xid = xid; } // ✅ setter
}


