package com.serverutvlab.services.SModels;

/**
 * Created by cj on 2016-11-18.
 */
public class SUser {
    private int id;
    private String email;

    public SUser(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
