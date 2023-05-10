package com.moutamid.ewastemanagement.models;

public class RecommendedModel {
    String name;
    int logo, image;

    public RecommendedModel(String name, int logo, int image) {
        this.name = name;
        this.logo = logo;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
