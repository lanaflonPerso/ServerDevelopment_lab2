package friendmanagment;

import backend.RestBackendLink;
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
public class RestTestBean implements Serializable {

//    private List<ShowUserVM> userList;
//    private DualListModel<ShowUserVM> userPL;

//    @ManagedProperty("#{userService}")
//    private UserService userService;

    @PostConstruct
    public void init() {

    }

    public String getJson() {
        return RestBackendLink.doRestCall("services/userservice/","users");
    }

}
