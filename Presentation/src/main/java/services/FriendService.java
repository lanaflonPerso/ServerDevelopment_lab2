package services;

import account.Account;
import restservices.BackendFacade;
import viewmodel.FriendVM;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by o_0 on 2016-11-18.
 */
@ManagedBean(name = "friendService")
@SessionScoped
public class FriendService {
    @ManagedProperty("#{account}")
    private Account userAccount;

    private ArrayList<FriendVM> friendList = new ArrayList<FriendVM>();
    private ArrayList<FriendVM> nonFriendList  =  new ArrayList<FriendVM>();

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    public boolean loadFriends() {
        ArrayList<FriendVM> result = BackendFacade.loadFriends(userAccount.getUserId());
        friendList = result;

        return true;
    }
    public List<FriendVM> loadNonFriends() {
        this.nonFriendList = BackendFacade.loadNonFriends(userAccount.getUserId());
        return nonFriendList;
    }

    public ArrayList<FriendVM> getNonFriendList() {
        return nonFriendList;
    }

    public List<FriendVM> getFriendList() {
        return friendList;
    }

    public boolean makeFriend(final int friendId) {
        if(BackendFacade.addFriend(userAccount.getUserId(),friendId)) {
            return true;
        }
        return false;
    }

    public void removeFriend(int selectedUserId){
        if (userAccount.getUserId() != selectedUserId)
            BackendFacade.removeFriend(userAccount.getUserId(),selectedUserId);
    }

}
