package backend;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.Path;

/**
 * Created by o_0 on 2016-11-17.
 */
@ManagedBean
@SessionScoped
public class DataConn {
    public String getUsers() {
        return RestBackendLink.getUsers();
    }
}
