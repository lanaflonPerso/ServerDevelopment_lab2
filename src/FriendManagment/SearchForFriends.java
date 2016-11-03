package FriendManagment;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created by o_0 on 2016-11-03.
 */
@ManagedBean
@ViewScoped
public class SearchForFriends implements Serializable {

    private List<ShowUserVM> userList;

    @ManagedProperty("#{userService}")
    private UserService userService;

    @PostConstruct
    public void init() {
        System.out.println("SearchForFriends:init running");
        userList = userService.getUserList();
        for (ShowUserVM u : userList) {
            System.out.println("\tUser list: " + u.getName());
        }
        System.out.println("SearchForFriends:init completed");
    }

    public List<ShowUserVM> getUserList() {
        for (ShowUserVM u : userList) {
            System.out.println("\tgetUserList:User list: " + u.getName());
        }
        return userList;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
