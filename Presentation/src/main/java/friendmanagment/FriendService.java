package friendmanagment;

import backend.RestBackendLink;
import com.google.gson.reflect.TypeToken;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by o_0 on 2016-11-18.
 */
@ManagedBean(name = "friendService")
@SessionScoped
public class FriendService {
    private ArrayList<FriendVM>  friendList = new ArrayList<FriendVM>();

    public boolean loadFriends(int userId) {
        String data = RestBackendLink.doRestGet("business/businessservice/","friends");
        Type type = new TypeToken<ArrayList<FriendVM>>(){}.getType();
        ArrayList<FriendVM> friends = RestBackendLink.parseJsonData(type, data);
        if (friends == null) {
            return false;
        }
        this.friendList = friends;
        return true;
    }

    public List<FriendVM> getFriendList() {
        return friendList;
    }
}
