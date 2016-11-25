package com.serverutvlab.business.BModels;

import java.util.List;

/**
 * Created by cj on 2016-11-17.
 */
public class BUser {
    private int id;
    private int profileId;
    private String email;
    private String password;
    private List<BUser> friends;


    public BUser(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public BUser(int id, int profileId, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BUser user = (BUser) o;

        if (id != user.id) return false;
        if (profileId != user.profileId) return false;
        return email != null ? email.equals(user.email) : user.email == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + profileId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
