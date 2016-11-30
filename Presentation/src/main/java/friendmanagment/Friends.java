package friendmanagment;

import account.Account;
import services.FriendService;
import services.NavigationService;
import viewmodel.FriendVM;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by o_0 on 2016-11-17.
 */
@ManagedBean
@ViewScoped
public class Friends {

    @ManagedProperty("#{account}")
    private Account userAccount;

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    @ManagedProperty("#{friendService}")
    private FriendService friendService;

    @ManagedProperty("#{navigationService}")
    private NavigationService navigationService;

    public NavigationService getNavigationService() {
        return navigationService;
    }

    public void setNavigationService(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    public FriendService getFriendService() {
        return friendService;
    }

    public void setFriendService(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostConstruct
    public void init() {
        if (userAccount.isLoggedin()) {
            friendService.loadFriends();
        } else {
            System.out.println("Friends:init:not logedin ");
        }
    }

    public void updateFriends() {
        if (userAccount.isLoggedin()) {
            friendService.loadFriends();
        }
    }

    public void updateNonFriends() {
        if (userAccount.isLoggedin()) {
            friendService.loadNonFriends();
        }
    }

    public List<FriendVM> getFriendList() {
        return friendService.getFriendList();
    }

    public void removeFriend() {
        friendService.removeFriend(navigationService.getSelectedUserId());
    }
}
