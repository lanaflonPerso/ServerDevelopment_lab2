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

    private List<FriendVM> nonFriends;

    @ManagedProperty("#{friendService}")
    private FriendService friendService;

    public FriendService getFriendService() {
        return friendService;
    }

    public void setFriendService(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostConstruct
    public void init() {
        this.nonFriends = friendService.loadNonFriends();

    }

    public List<FriendVM> getNonFriends() {
        return nonFriends;
    }

    public void setNonFriends(List<FriendVM> nonFriends) {
        this.nonFriends = nonFriends;
    }

    public void makeNewFriends(int friendId) {
        if(friendService.makeFriend(friendId)) {
            nonFriends = friendService.getNonFriendList();
        }
    }

}
