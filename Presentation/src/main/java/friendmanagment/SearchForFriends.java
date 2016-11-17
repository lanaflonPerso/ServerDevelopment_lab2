package friendmanagment;

import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.view.facelets.FaceletContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by o_0 on 2016-11-03.
 */
@ManagedBean
@ViewScoped
public class SearchForFriends implements Serializable {

    private List<ShowUserVM> userList;
    private DualListModel<ShowUserVM> userPL;

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
        List<ShowUserVM> tmp1 = new ArrayList<ShowUserVM>() ;
        List<ShowUserVM> tmp2 = new ArrayList<ShowUserVM>() ;
        tmp1.addAll(userList);
        userPL = new DualListModel<ShowUserVM>(tmp1,tmp2);
    }

    public List<ShowUserVM> getUserList() {
        for (ShowUserVM u : userList) {
            System.out.println("\tgetUserList:User list: " + u.getName());
        }
        return userList;
    }

    public void makeNewFriends(List<String> friends) {
        for (String u : friends) {
            System.out.println("makeNewFriends:" + u);
        }
    }

    public DualListModel<ShowUserVM> getUserPL() {
        return userPL;
    }

    public void setUserPL(DualListModel<ShowUserVM> userPL) {
        this.userPL = userPL;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
