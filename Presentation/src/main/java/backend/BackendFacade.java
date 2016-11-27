package backend;

import backend.SModels.SPost;
import backend.SModels.SProfile;
import backend.SModels.SUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import friendmanagment.FriendVM;
import viewmodel.PostVM;
import viewmodel.ProfileItem;
import viewmodel.ProfileVM;
import viewmodel.UserVM;
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
     *  Call to backend to authenticate a user
     * @param userName
     * @param password
     * @return returning the user if success
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

    /**
     * catt to server to registrate a user
     * @param email
     * @param password
     * @return the new user that was created
     */
    public static UserVM registerUser(String email, String password){
        Map<String,Object> parameters = new LinkedHashMap<String,Object>();
        parameters.put("email", email);
        parameters.put("password", password);

        String jsonResp = RestBackendLink.doRestParmPost(pathUserService,"registerUser", parameters);
        System.out.println("registerUser response: "+jsonResp);
        if (jsonResp.equals("null"))
            return null;

        Type type = new TypeToken<SUser>(){}.getType();
        SUser user = RestBackendLink.parseJsonData(type, jsonResp);

        UserVM result = new UserVM(user.getId(),user.getEmail(),user.getProfileId());

        return result;

    }

    /**
     * get user from server with userId
     * @param userId
     * @return
     */
    public static UserVM getUser(int userId){
        Map<String,Object> parameters = new LinkedHashMap<String,Object>();
        parameters.put("id", userId);

        String jsonResp = RestBackendLink.doRestParmPost(pathUserService,"userById", parameters);
        if (jsonResp.equals("null"))
            return null;

        Type type = new TypeToken<SUser>(){}.getType();
        SUser user = RestBackendLink.parseJsonData(type, jsonResp);
        if (user == null) {
            System.out.println("getUser returned null");
            return null;
        }

        UserVM result = new UserVM(user.getId(),user.getEmail(),user.getProfileId());

        return result;

    }


    /**
     * call to server to create a sacred friendship between these to lovers
     * @param userId
     * @param friendId
     * @return
     */
    public static boolean addFriend(int userId,int friendId){
        Map<String,Object> parameters = new LinkedHashMap<String,Object>();
        parameters.put("userId", userId);
        parameters.put("friendId", friendId);

        String jsonResp = RestBackendLink.doRestParmPost(pathUserService,"addFriendToUser", parameters);

        if (jsonResp.equals("null")) {
            return false;
        }

        System.out.println("saveProfile: response: " + jsonResp);

        Type type = new TypeToken<Map<String, Boolean>>(){}.getType();
        Map<String, Boolean> res = RestBackendLink.parseJsonData(type, jsonResp);

        return res.get("success");

    }

    /**
     * call to server to remove this sacred friendship
     * @param userId
     * @param friendId
     * @return
     */
    public static boolean removeFriend(int userId,int friendId){
        Map<String,Object> parameters = new LinkedHashMap<String,Object>();
        parameters.put("userId", userId);
        parameters.put("friendId", friendId);

        String jsonResp = RestBackendLink.doRestParmPost(pathUserService,"removeFriend", parameters);

        if (jsonResp.equals("null")) {
            return false;
        }

        System.out.println("saveProfile: response: " + jsonResp);

        Type type = new TypeToken<Map<String, Boolean>>(){}.getType();
        Map<String, Boolean> res = RestBackendLink.parseJsonData(type, jsonResp);

        return res.get("success");
    }




    /**
     * call to server to get all users friends
     * @param userId
     * @return
     */
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

    /**
     * call to server to get all users NonFriends
     * @param userId
     * @return
     */
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


    /**
     * call to load a user profile
     * @param userId
     * @return
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

    /**
     * call to update profile
     * @param userId
     * @param profileInfo
     * @return
     */
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

    /**
     * call to get the users post feed
     * @param userId
     * @return
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

    /**
     * call to get posts for a profile wall. This will include all the posts that the user are allowed to see.
     * some posts might be private between two users
     * @param profileId
     * @param activeUserId
     * @return
     */
    public static List<PostVM> getPostForProfile(int profileId, int activeUserId){

        Map<String,Object> parameters = new LinkedHashMap<String,Object>();
        parameters.put("profileId", profileId);
        parameters.put("activeUserId", activeUserId);

        String jsonResp = RestBackendLink.doRestGet(pathPostService,"getprofileposts",parameters);
        if (jsonResp.equals("[]"))
            return null;

        Type type = new TypeToken<ArrayList<SPost>>(){}.getType();
        ArrayList<SPost> result = RestBackendLink.parseJsonData(type,jsonResp);
        ArrayList<PostVM> wallPosts = new ArrayList<PostVM>();
        if (result == null) {
            System.out.println("loaded wallposts: null result");
            return wallPosts;
        }
        System.out.println("loaded wallposts: " + result.toString());



        for (SPost p: result){
            wallPosts.add(new PostVM(
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
        return wallPosts;
    }


    /**
     * call to post a post on someones wall, including your own
     * @param authorId
     * @param recipientId
     * @param subject
     * @param messageBody
     * @param isPrivate
     * @return
     */
    public static PostVM postPost(int authorId,int recipientId,String subject,String messageBody,boolean isPrivate){
        Map<String,Object> parameters = new LinkedHashMap<String,Object>();
        parameters.put("authorId", authorId );
        parameters.put("recipientId", recipientId);
        parameters.put("subject", subject);
        parameters.put("messageBody", messageBody);
        parameters.put("isPrivate", isPrivate);

        String jsonResp = RestBackendLink.doRestParmPost(pathPostService,"postposttoprofile",parameters);
        if (jsonResp.equals("null")){
            return null;
        }

        Type type = new TypeToken<SPost>(){}.getType();
        SPost post = RestBackendLink.parseJsonData(type,jsonResp);

        if (post == null)
            return null;

        return new PostVM(
                post.getId(),
                post.getSubject(),
                post.getMessageBody(),
                post.getAuthorName(),
                post.getRecipientName(),
                post.getTimestamp(),
                post.getAuthorId(),
                post.getRecipientId(),
                post.isPrivate());

    }

}
