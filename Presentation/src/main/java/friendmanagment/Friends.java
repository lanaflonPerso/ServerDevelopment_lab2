package friendmanagment;

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

    @ManagedProperty("#{friendService}")
    private FriendService service;

    public void setService(FriendService service) {
        this.service = service;
    }

    @PostConstruct
    public void init() {
        String s = FacesContext.getCurrentInstance().getCurrentPhaseId().toString();
        System.out.println("Friends:FacesContext phaseId: "+ s );
        service.loadFriends(0);
        friendList = service.getFriendList();
    }

    public List<FriendVM> getFriendList() {
        return friendList;
    }
}
