package com.example.vacationrecommender.dto;

public class Breadcrumb {
    private String label;
    private String url;

    public Breadcrumb(String label, String url) {
        this.label = label;
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public String getUrl() {
        return url;
    }
}
