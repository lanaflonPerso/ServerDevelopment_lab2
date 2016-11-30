package navigationcontroller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by o_0 on 2016-11-30.
 */
@ManagedBean(name = "navigationService")
@SessionScoped
public class NavigationService {
    private int selectedUserId;

    public int getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(int selectedUserId) {
        this.selectedUserId = selectedUserId;
    }
}
