package backend;

import account.Account;
import viewmodel.PostVM;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cj on 2016-11-27.
 */
@ManagedBean(name = "accountService")
@SessionScoped
public class AccountService {


    private int userId;
    private String username;
    private int profileId;

    @ManagedProperty("#{account}")
    private Account userAccount;


    @PostConstruct
    public void init() {
        if (userAccount == null) {
            System.out.println("inint error (userAccount == null) ");
            return;
        }
        this.userId = userAccount.getUserId();
        this.profileId = -1;

    }


    public List<PostVM> getNewsFeed(){
        return BackendFacade.loadFeed(userId);
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

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }
}
