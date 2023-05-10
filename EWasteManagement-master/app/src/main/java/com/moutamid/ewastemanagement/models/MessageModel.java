package com.moutamid.ewastemanagement.models;

public class MessageModel {
    String date, name, last_message;
    int image;

    public MessageModel(String date, String name, String last_message, int image) {
        this.date = date;
        this.name = name;
        this.last_message = last_message;
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_message() {
        return last_message;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
