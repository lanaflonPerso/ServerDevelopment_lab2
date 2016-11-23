package friendmanagment;
import backend.RestBackendLink;
import backend.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by o_0 on 2016-11-03.
 */
public class DummyUserFacade {
    static List<ShowUserVM> getUsers() {
        List<UserEntity> usersAll = RestBackendLink.getUsersAll();
        List<ShowUserVM> list = new ArrayList<ShowUserVM>();
        for (UserEntity ue : usersAll ) {
            list.add(new ShowUserVM(ue.getEmail()));
        }

        return list;
    }
}
