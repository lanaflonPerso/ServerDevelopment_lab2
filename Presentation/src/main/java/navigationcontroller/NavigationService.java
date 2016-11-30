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
    private int selectedProfileId;


    public int getSelectedUserId() {
        return selectedUserId;
    }

    public int getSelectedProfileId() {
        return selectedProfileId;
    }

    public void setSelectedUserId(int selectedUserId) {
        this.selectedUserId = selectedUserId;
    }

    public void setSelectedProfileId(int selectedProfileId) {
        this.selectedProfileId = selectedProfileId;
    }


}
