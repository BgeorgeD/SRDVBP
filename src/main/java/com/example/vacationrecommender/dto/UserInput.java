package com.example.vacationrecommender.dto;

import java.util.List;

public class UserInput {
    private String tip_vacanta;
    private int buget;
    private List<String> preferinte;

    public String getTip_vacanta() {
        return tip_vacanta;
    }

    public void setTip_vacanta(String tip_vacanta) {
        this.tip_vacanta = tip_vacanta;
    }

    public int getBuget() {
        return buget;
    }

    public void setBuget(int buget) {
        this.buget = buget;
    }

    public List<String> getPreferinte() {
        return preferinte;
    }

    public void setPreferinte(List<String> preferinte) {
        this.preferinte = preferinte;
    }
}
