package friendmanagment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by o_0 on 2016-11-03.
 */
public class DummyUserFacade {
    static List<ShowUserVM> getUsers() {
        List<ShowUserVM> list = new ArrayList<ShowUserVM>();
        for (int i = 0; i < 10; i++) {
            list.add(new ShowUserVM("User" + i));
        }
        return list;
    }
}
