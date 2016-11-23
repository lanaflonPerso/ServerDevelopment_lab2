package com.serverutvlab.services.SModels;

import java.util.List;

/**
 * Created by cj on 2016-11-18.
 */
public class SUser {
    private int id;
    private String email;
    private List<SUser> friends;

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

    public List<SUser> getFriends() {
        return friends;
    }

    public void setFriends(List<SUser> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "SUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
