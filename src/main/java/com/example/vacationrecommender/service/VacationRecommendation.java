package com.example.vacationrecommender.service;

public class VacationRecommendation {
    private String atractii;
    private String destinatie;
    private String durata;
    private String hotel;
    private String numePachet;
    private String orasPlecare;
    private int pret;
    private String tipPachet;
    private int mese;
    private int zboruri;
    private String imagine;

    public String getImagine() {
        return imagine;
    }
    public void setImagine(String imagine) {
        this.imagine = imagine;
    }

    public String getAtractii() { return atractii; }
    public void setAtractii(String atractii) { this.atractii = atractii; }

    public String getDestinatie() { return destinatie; }
    public void setDestinatie(String destinatie) { this.destinatie = destinatie; }

    public String getDurata() { return durata; }
    public void setDurata(String durata) { this.durata = durata; }

    public String getHotel() { return hotel; }
    public void setHotel(String hotel) { this.hotel = hotel; }

    public String getNumePachet() { return numePachet; }
    public void setNumePachet(String numePachet) { this.numePachet = numePachet; }

    public String getOrasPlecare() { return orasPlecare; }
    public void setOrasPlecare(String orasPlecare) { this.orasPlecare = orasPlecare; }

    public int getPret() { return pret; }
    public void setPret(int pret) { this.pret = pret; }

    public String getTipPachet() { return tipPachet; }
    public void setTipPachet(String tipPachet) { this.tipPachet = tipPachet; }

    public int getMese() { return mese; }
    public void setMese(int mese) { this.mese = mese; }

    public int getZboruri() { return zboruri; }
    public void setZboruri(int zboruri) { this.zboruri = zboruri; }
}