package com.example.trackerapp;

public class userModel {
    private String userEmail, userPassword, userId;

//make empty constructor

    public userModel() {
    }
    public userModel(String userEmail, String userPassword, String userId) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userId = userId;
    }

// Make getter and setter


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
