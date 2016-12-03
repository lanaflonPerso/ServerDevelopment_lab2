package restservices.SModels;

import java.util.List;

/**
 * Created by cj on 2016-11-18.
 */
public class SUser {
    private int id;
    private int profileId;
    private String email;
    private List<SUser> friends;

    public SUser(int id,int profileId, String email) {
        this.id = id;
        this.profileId = id;
        this.email = email;

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
