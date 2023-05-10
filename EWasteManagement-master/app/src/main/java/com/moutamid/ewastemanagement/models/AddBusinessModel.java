package com.moutamid.ewastemanagement.models;

public class AddBusinessModel {
    String businessName, callingNumber, image, category;
    double lat;
    double lng;

    public AddBusinessModel() {
    }

    public AddBusinessModel(String businessName, String callingNumber, String image, String category, double lat, double lng) {
        this.businessName = businessName;
        this.callingNumber = callingNumber;
        this.image = image;
        this.category = category;
        this.lat = lat;
        this.lng = lng;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCallingNumber() {
        return callingNumber;
    }

    public void setCallingNumber(String callingNumber) {
        this.callingNumber = callingNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
