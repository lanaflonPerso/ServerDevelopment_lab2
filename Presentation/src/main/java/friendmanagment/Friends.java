package friendmanagment;

import account.Account;
import backend.BackendFacade;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by o_0 on 2016-11-17.
 */
@ManagedBean
@ViewScoped
public class Friends {
    List<FriendVM> friendList;

//    @ManagedProperty("#{friendService}")
//    private FriendService service;

    @ManagedProperty("#{account}")
    private Account userAccount;

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }



    @PostConstruct
    public void init() {
        if (userAccount.isLoggedin()) {
            this.friendList = BackendFacade.loadFriends(userAccount.getUserId());

        }else {
            this.friendList = new ArrayList<FriendVM>();
        }
//
//        service.loadFriends(0);
//        friendList = service.getFriendList();
    }

    public void updateFriends() {
        if (userAccount.isLoggedin()) {
            this.friendList = BackendFacade.loadFriends(userAccount.getUserId());
        }
    }

    public List<FriendVM> getFriendList() {
        return friendList;
    }
}
