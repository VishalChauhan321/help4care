package com.moutamid.ewastemanagement.models;

public class ConversationModel {
    String message, time;
    int logo;

    public ConversationModel(String message, String time, int logo) {
        this.message = message;
        this.time = time;
        this.logo = logo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

}
