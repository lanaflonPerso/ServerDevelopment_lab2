package friendmanagment;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by o_0 on 2016-11-03.
 */
@SessionScoped
@ManagedBean
public class ShowUserVM implements Serializable {
    private String name;

    public ShowUserVM() {
    }

    public ShowUserVM(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ShowUserVM{" +
                "name='" + name + '\'' +
                '}';
    }
}
