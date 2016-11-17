package navigationcontroller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Created by cj on 2016-11-17.
 */
@ManagedBean
@ViewScoped
public class Bean implements Serializable {

    private String page;

    @PostConstruct
    public void init() {
        page = "GetNewFriends"; //  Default include.
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}