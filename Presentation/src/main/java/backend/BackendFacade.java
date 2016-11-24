package backend;

import backend.SModels.SProfile;
import backend.SModels.SUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import friendmanagment.FriendVM;
import viewmodel.ProfileVM;
import viewmodel.UserVM;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by o_0 on 2016-11-23.
 */
public class BackendFacade {
    public static UserVM loginUser(String userName, String password) {
        // TODO: make restcall to backend
        System.out.println("BackendFacade::loginUser user:" + userName + " pass: " + password);
        String jsonResp = RestBackendLink.doRestParmPost("services/userservice/","login",
                "?email="+userName+"&password="+password);
        if (jsonResp.equals("")) {
            System.out.println("loginUser wrong");
            return null;
        }
        SUser sUser = new Gson().fromJson(jsonResp, SUser.class);


        return new UserVM(sUser.getId(),sUser.getEmail());
    }
    //    public ProfileVM(String name, String info, Integer age, int relationshipStatus) {

    public static ProfileVM loadProfile(int userId) {
        System.out.println("user id: " +userId );
        String jsonResp = RestBackendLink.doRestGet("services/profileservice/", "getProfile", "?userId=" + userId);
        System.out.println("Json response: " + jsonResp);
        SProfile sProfile = new Gson().fromJson(jsonResp, SProfile.class);
        System.out.println(sProfile.toString());
        return new ProfileVM(sProfile.getName(),sProfile.getInfo(),sProfile.getAge(),sProfile.getRelationshipStatus());
    }

    public static boolean saveProfile(int userId, ProfileVM profileInfo) {
        System.out.println("BackendFacade::saveProfile userId:" + userId + " profile: " + profileInfo.toString());
        String jsonResp = RestBackendLink.doRestParmPost("services/profileservice/","updateProfile",
                "?userId="+userId +
                        "&username="+profileInfo.getName() +
                        "&info="+profileInfo.getInfo() +
                        "&relationshipStatus="+profileInfo.getRelationshipStatus() +
                        "&age="+profileInfo.getAge());
        if (jsonResp.equals("")) {
            System.out.println("loginUser wrong");
            return false;
        }

        System.out.println("saveProfile: response: " + jsonResp);

        Type type = new TypeToken<Map<String, Boolean>>(){}.getType();
        Map<String, Boolean> res = RestBackendLink.parseJsonData(type, jsonResp);


//        return new UserVM(sUser.getId(),sUser.getEmail());
        return true;
    }

    public static ArrayList<FriendVM> loadFriends(int userId) {

        //getFriendsByUserId
        String data = RestBackendLink.doRestGet("services/userservice/","getFriendsByUserId","?userId=" + userId);
        Type type = new TypeToken<ArrayList<SUser>>(){}.getType();
        ArrayList<SUser> friends = RestBackendLink.parseJsonData(type, data);
        if (friends == null) {
            return null;
        }
        ArrayList<FriendVM> result = new ArrayList<FriendVM>();
        for (SUser u : friends) {
            result.add(new FriendVM(u.getId(),u.getEmail()));
        }
        return result;
    }
}
