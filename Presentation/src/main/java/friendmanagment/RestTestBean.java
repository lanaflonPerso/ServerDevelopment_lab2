package friendmanagment;

import backend.RestBackendLink;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Created by o_0 on 2016-11-03.
 */
@ManagedBean
@ViewScoped
public class RestTestBean implements Serializable {

//    private List<ShowUserVM> userList;
//    private DualListModel<ShowUserVM> userPL;

//    @ManagedProperty("#{userService}")
//    private UserService userService;

    @PostConstruct
    public void init() {

    }

    public String getJson() {
        return RestBackendLink.doRestGet("services/userservice/","users");
    }

}
