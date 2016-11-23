package com.serverutvlab.business.BModels;

import java.util.List;

/**
 * Created by cj on 2016-11-17.
 */
public class BUser {
    private int id;
    private String email;
    private String password;
    private List<BUser> friends;


    public BUser(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BUser> getFriends() {
        return friends;
    }

    public void setFriends(List<BUser> friends) {
        this.friends = friends;
    }
}
