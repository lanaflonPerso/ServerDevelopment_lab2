package com.presentation.FriendManagment;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

/**
 * Created by o_0 on 2016-11-03.
 */
@ManagedBean(name = "userService")
@ApplicationScoped
public class UserService implements Serializable {
    public List<ShowUserVM> getUserList() {
        return DummyUserFacade.getUsers();
    }

    public void makeNewFriends(List<ShowUserVM> friends) {

    }

}
