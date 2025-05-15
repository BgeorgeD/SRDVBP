/*package com.example.vacationrecommender.dto;

public class VacationDTO {
    private String name;
    private String imageUrl;
    private String location;
    private double price;
    private String link;

    // Constructor gol (obligatoriu pentru Spring și JSON)
    public VacationDTO() {
    }

    // Getteri și setteri
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
 */
package com.example.vacationrecommender.dto;

public class VacationDTO {
    private String destinatie;
    private String descriere;
    private String tip;
    private int pret;
    private String imageUrl;

    public VacationDTO() {}

    public VacationDTO(String destinatie, String descriere, String tip, int pret, String imageUrl) {
        this.destinatie = destinatie;
        this.descriere = descriere;
        this.tip = tip;
        this.pret = pret;
        this.imageUrl = imageUrl;
    }

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}



