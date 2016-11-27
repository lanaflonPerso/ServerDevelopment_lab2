package view;

import account.Account;
import backend.AccountService;
import backend.ProfileService;
import viewmodel.PostVM;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by cj on 2016-11-27.
 */
@ManagedBean
@ViewScoped
public class FeedView {

    private List<PostVM> feed;

    @PostConstruct
    public void init() {
        System.out.println("ProfileView::init");
        if (accountService == null)
            return;

    }



    @ManagedProperty("#{accountService}")
    private AccountService accountService;


    public List<PostVM> getFeed() {
        System.out.println("geting newsFeed");
        feed = accountService.getNewsFeed();
        return feed;
    }

    public void setFeed(List<PostVM> feed) {
        this.feed = feed;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
