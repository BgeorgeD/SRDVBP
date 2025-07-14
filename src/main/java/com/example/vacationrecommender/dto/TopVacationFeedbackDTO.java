package com.example.vacationrecommender.dto;

public class TopVacationFeedbackDTO {

    private String numePachet;
    private String orasStart;
    private String comentariu;
    private int rating;
    private String username;

    public TopVacationFeedbackDTO() {
    }

    public String getNumePachet() {
        return numePachet;
    }

    public void setNumePachet(String numePachet) {
        this.numePachet = numePachet;
    }

    public String getOrasStart() {
        return orasStart;
    }

    public void setOrasStart(String orasStart) {
        this.orasStart = orasStart;
    }

    public String getComentariu() {
        return comentariu;
    }

    public void setComentariu(String comentariu) {
        this.comentariu = comentariu;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "TopVacationFeedbackDTO{" +
                "numePachet='" + numePachet + '\'' +
                ", orasStart='" + orasStart + '\'' +
                ", comentariu='" + comentariu + '\'' +
                ", rating=" + rating +
                ", username='" + username + '\'' +
                '}';
    }
}
