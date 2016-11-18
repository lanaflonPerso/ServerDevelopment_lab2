package navigationcontroller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Created by cj on 2016-11-17.
 */
@ManagedBean
@SessionScoped
public class Bean implements Serializable {

    private String page;
    private String friendpage;

    @PostConstruct
    public void init() {
        page = "GetNewFriends"; friendpage = "friends";
        //  Default include.
    }

    public String getFriendpage() {
        return friendpage;
    }

    public void setFriendpage(String friendpage) {
        this.friendpage = friendpage;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}