package services;

import account.Account;
import restservices.BackendFacade;
import viewmodel.PostVM;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created by cj on 2016-11-27.
 */
@ManagedBean(name = "feedService")
@SessionScoped
public class FeedService {

    @ManagedProperty("#{account}")
    private Account userAccount;

    @PostConstruct
    public void init() {
        if (userAccount == null) {
            System.out.println("inint error (userAccount == null) ");
            return;
        }
    }


    public List<PostVM> getNewsFeed(){
        return BackendFacade.loadFeed(userAccount.getUserId());
    }


    public List<PostVM> getProfileFeed(int selectedUserId) {
        System.out.println("FeedService:getProfileFeed");
        List<PostVM> postForProfile = BackendFacade.getSelectedProfilePost(selectedUserId,userAccount.getUserId());

        return postForProfile;
    }


    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }
}
