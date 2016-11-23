package backend;

import backend.SModels.SProfile;
import backend.SModels.SUser;
import com.google.gson.Gson;
import viewmodel.ProfileVM;
import viewmodel.UserVM;

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
}
