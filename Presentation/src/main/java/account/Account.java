package account;

import viewmodel.UserVM;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by o_0 on 2016-11-23.
 */
@ManagedBean
@SessionScoped
public class Account {
    private int userId;
    private String username;
    private int profileId;
    private boolean loggedin;

    @PostConstruct
    public void init() {
        this.loggedin = false;
        this.username = "none";
        this.userId = 0;
        this.profileId = 0;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public void fillAccount(UserVM user) {
        this.username = user.getUsername();
        this.userId = user.getUserId();
        this.profileId = 0;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", loggedin=" + loggedin +
                '}';
    }
}
