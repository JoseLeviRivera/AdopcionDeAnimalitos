package com.example.adopciondeanimales.Models;

public class User {
    private String userID;
    private String email;
    private String password;
    private boolean active;
    private String token;

    public User(String userId, String token, String email, String password, boolean active) {
        this.userID = userId;
        this.email = email;
        this.password = password;
        this.active = active;
        this.token = token;
    }

    public User() {

    }


    public String getToken() {
        return token;
    }

    public void setToken(String id) {
        this.token = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
