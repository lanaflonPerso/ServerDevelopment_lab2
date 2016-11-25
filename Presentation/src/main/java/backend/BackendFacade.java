package backend;

import backend.SModels.SPost;
import backend.SModels.SProfile;
import backend.SModels.SUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import friendmanagment.FriendVM;
import viewmodel.PostVM;
import viewmodel.ProfileItem;
import viewmodel.ProfileVM;
import viewmodel.UserVM;

import javax.ws.rs.Path;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by o_0 on 2016-11-23.
 */
public class BackendFacade {
    private static final String pathUserService = "services/userservice/";
    private static final String pathProfileService = "services/profileservice/";
    private static final String pathPostService = "services/postservice/";


    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * * * * * * *   USER SERVICE BACKEND CALLS!
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     *
     * @param userName
     * @param password
     * @return
     */
    public static UserVM loginUser(String userName, String password) {
        System.out.println("BackendFacade::loginUser user:" + userName + " pass: " + password);

        Map<String,Object> parameters = new LinkedHashMap<String,Object>();
        parameters.put("email", userName);
        parameters.put("password", password);

        String jsonResp = RestBackendLink.doRestParmPost(pathUserService,"login", parameters);

        if (jsonResp.equals("null")) {
            System.out.println("loginUser wrong");
            return null;
        }
        SUser sUser = new Gson().fromJson(jsonResp, SUser.class);

        return new UserVM(sUser.getId(),sUser.getEmail(),sUser.getProfileId());
    }

    public static UserVM registerUser(String email, String password){
        Map<String,Object> parameters = new LinkedHashMap<String,Object>();
        parameters.put("email", email);
        parameters.put("password", password);

        String jsonResp = RestBackendLink.doRestParmPost(pathUserService,"registerProfile", parameters);
        System.out.println("registerUser response: "+jsonResp);
        if (jsonResp.equals("null"))
            return null;

        Type type = new TypeToken<SUser>(){}.getType();
        SUser user = RestBackendLink.parseJsonData(type, jsonResp);

        UserVM result = new UserVM(user.getId(),user.getEmail(),user.getProfileId());

        return result;

    }


    public static UserVM getUser(int userId){
        Map<String,Object> parameters = new LinkedHashMap<String,Object>();
        parameters.put("userId", userId);

        String jsonResp = RestBackendLink.doRestGet(pathUserService,"userById", parameters);
        if (jsonResp.equals("null"))
            return null;

        Type type = new TypeToken<SUser>(){}.getType();
        SUser user = RestBackendLink.parseJsonData(type, jsonResp);

        UserVM result = new UserVM(user.getId(),user.getEmail(),user.getProfileId());

        return result;

    }

    public static ArrayList<FriendVM> loadFriends(int userId) {
        Map<String,Object> parameters = new LinkedHashMap<String,Object>();
        parameters.put("userId", userId);

        //getFriendsByUserId
        String data = RestBackendLink.doRestGet(pathUserService,"getFriendsByUserId",parameters);
        if (data.equals("null"))
            return null;

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

    public static ArrayList<FriendVM> loadNonFriends(int userId){
        Map<String,Object> parameters = new LinkedHashMap<String,Object>();
        parameters.put("userId", userId);

        //getFriendsByUserId
        String data = RestBackendLink.doRestGet(pathUserService,"getNonFriends",parameters);
        if (data.equals("null"))
            return null;

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


    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * * * * * * *   PROFILE SERVICE BACKEND CALLS!
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    public static ProfileItem loadProfile(int userId) {
        System.out.println("user id: " +userId );
        Map<String,Object> parameters = new LinkedHashMap<String,Object>();
        parameters.put("userId", userId);

        String jsonResp = RestBackendLink.doRestGet(pathProfileService, "getProfile", parameters);
        System.out.println("Json response: " + jsonResp);
        if (jsonResp.equals("null")) {
            return null;
        }
        SProfile sProfile = new Gson().fromJson(jsonResp, SProfile.class);
        if (sProfile == null) {
            return null;
        }
        System.out.println(sProfile.toString());
        return new ProfileVM(sProfile.getId(),sProfile.getName(),sProfile.getInfo(),sProfile.getAge(),sProfile.getRelationshipStatus());
    }

    public static boolean saveProfile(int userId, ProfileItem profileInfo) {
        System.out.println("BackendFacade::saveProfile userId:" + userId + " profile: " + profileInfo.toString());

        Map<String,Object> parameters = new LinkedHashMap<String,Object>();
        parameters.put("userId", userId);
        parameters.put("username", profileInfo.getName());
        parameters.put("info", profileInfo.getInfo());
        parameters.put("relationshipStatus", profileInfo.getRelationshipStatus());
        parameters.put("age", profileInfo.getAge());

        String jsonResp = RestBackendLink.doRestParmPost(pathProfileService,"updateProfile", parameters);

        if (jsonResp.equals("null")) {
            return false;
        }

        System.out.println("saveProfile: response: " + jsonResp);

        Type type = new TypeToken<Map<String, Boolean>>(){}.getType();
        Map<String, Boolean> res = RestBackendLink.parseJsonData(type, jsonResp);

        return res.get("success");
    }


    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * * * * * * *   POST SERVICE BACKEND CALLS!
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    public static List<PostVM> loadFeed(int userId){
        Map<String,Object> parameters = new LinkedHashMap<String,Object>();
        parameters.put("userId", userId);

        String jsonResp = RestBackendLink.doRestGet(pathPostService,"getFeed",parameters);
        if (jsonResp.equals("[]"))
            return null;


        Type type = new TypeToken<ArrayList<SPost>>(){}.getType();
        ArrayList<SPost> result = RestBackendLink.parseJsonData(type,jsonResp);
        System.out.println("loaded feed: " + result.toString());


        ArrayList<PostVM> feed = new ArrayList<PostVM>();
        for (SPost p: result){
            feed.add(new PostVM(
                    p.getId(),
                    p.getSubject(),
                    p.getMessageBody(),
                    p.getAuthorName(),
                    p.getRecipientName(),
                    p.getTimestamp(),
                    p.getAuthorId(),
                    p.getRecipientId(),
                    p.isPrivate())
            );
        }

        return feed;
    }


    public static PostVM postPost(int auhtoridId,int recipientId,String subject,String messageBody,boolean isPrivate){
        Map<String,Object> parameters = new LinkedHashMap<String,Object>();
        parameters.put("authorId", auhtoridId );
        parameters.put("recipientId", recipientId);
        parameters.put("subject", subject);
        parameters.put("messageBody", messageBody);
        parameters.put("isPrivate", isPrivate);

        String jsonResp = RestBackendLink.doRestGet(pathPostService,"postpostroprofile",parameters);
        if (jsonResp.equals("null")){
            return null;
        }


        return null;

    }








}
