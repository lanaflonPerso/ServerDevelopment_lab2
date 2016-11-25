package friendmanagment;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by o_0 on 2016-11-18.
 */
@SessionScoped
@ManagedBean
public class FriendVM {
    private int id; // är det här userId ??
    private String name;

    public FriendVM(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public FriendVM() {
        this.id = 0;
        this.name = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FriendVM{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
