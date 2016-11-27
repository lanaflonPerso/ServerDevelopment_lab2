package com.serverutvlab.database.DBLayer;


import com.serverutvlab.business.BModels.BPost;
import com.serverutvlab.business.BModels.BProfile;
import com.serverutvlab.business.BModels.BUser;
import com.serverutvlab.database.DBModels.PostEntity;
import com.serverutvlab.database.DBModels.ProfileEntity;
import com.serverutvlab.database.DBModels.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by o_0 on 2016-11-10.
 */
public class DBFacade {


    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *  USERENTITY LOGIC CALLS
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * get all users
     * @return all users
     */
    static public List<BUser> getAllUsers() {
        List<UserEntity> entities = new UserLogic().getAllUsers();
        List<BUser> result = new ArrayList<BUser>();
        for (UserEntity e: entities)
            result.add(new BUser(e.getId(),e.getEmail(),e.getPassword()));
        return result;
    }

    /**
     * create a new user with email and password
     * @param email
     * @param password
     * @return user or null
     */
    static public BUser createNewUser(String email, String password) {
        UserEntity user = new UserLogic().createNewAccount(email,password);
        if (user == null)
            return null;

        return new BUser(user.getId(),user.getEmail(),user.getPassword());
    }

    /**
     * authenticates user
     * @param e = email
     * @param p = password
     * @return user or null
     */
    public static BUser authenticateUser(String e, String p) {
        return new UserLogic().authenticateUser(e, p);
    }

    /**
     * gets user by id
     * @param id
     * @return user
     */
    public static BUser getUserById(int id) {
        UserEntity user = new UserLogic().getUserById(id);
        if (user == null)
            return null;
        return new BUser(user.getId(),user.getEmail(),user.getPassword());
    }

    /**
     * get friends by user
     * @param id
     * @return friends
     */
    public static List<BUser> getFriendsByUserId(int id) {
        List<BUser> friends = new ArrayList<BUser>();
        List<UserEntity> friendsByUserId = new UserLogic().getFriendsByUserId(id);
        if (friendsByUserId == null)
            return null;

        for(UserEntity u: friendsByUserId ){
            friends.add(new BUser(u.getId(),u.getEmail(),u.getPassword()));
        }
        return friends;
    }

    /**
     * add friendship between id's
     * @param uId
     * @param fId
     * @return
     */
    public static boolean addFriendToUser(int uId, int fId) {
        return new UserLogic().addFriendToUser(uId,fId);
    }

    /**
     * remove friendship between id's
     * @param uId
     * @param fId
     * @return
     */
    public static boolean removeFriend(int uId, int fId) {
        return new UserLogic().removeFriend(uId,fId);
    }
    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *  PROFILEENTITY LOGIC CALLS
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * get the profile by user Id
     * @param userId
     * @return profile
     */
    static public BProfile getProfileForUserId(int userId) {
        ProfileEntity profile = new ProfileLogic().getProfileByUserId(userId);
        List<BPost> posts = new ArrayList<BPost>();
        if (profile == null){
            return null;
        }
        for (PostEntity p : new PostLogic().getPostsByProfileId(profile.getId())) {
            posts.add(new BPost(p.getId(),p.getSubject(),p.getMessageBody(),p.getTimestamp(),p.getAuthorId(),p.getRecipientId()));
        }

        return new BProfile(
                profile.getId(),
                profile.getName(),
                profile.getInfo(),
                profile.getAge(),
                profile.getRelationshipStatus(),
                profile.getUserId(),
                posts
                );
    }

    /**
     * updates profile
     * @param userId
     * @param username
     * @param info
     * @param relationshipStatus
     * @param age
     * @return success
     */
    public static boolean updateProfile(int userId, String username, String info, int relationshipStatus, int age) {
        return new ProfileLogic().updateProfile(userId,username,info,relationshipStatus,age);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *  POSTENTITY LOCIG CALLS
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * get poosts for a profile,
     * @param profileId the profile id
     * @return list of posts
     */
    public static List<BPost> getPostsForProfile(int profileId) {
        List<BPost> posts = new ArrayList<BPost>();
        for(PostEntity e : new PostLogic().getPostsByProfileId(profileId)){
            posts.add(new BPost(e.getId(),e.getSubject(),e.getMessageBody(),e.getAuthorName(),e.getRecipientName(),e.getTimestamp(),e.getAuthorId(),e.getRecipientId(),e.isPrivate()));
        }
        return posts;
    }

    /**
     * call to post a post
     * @param authorId
     * @param recipientId
     * @param subject
     * @param messageBody
     * @param isPrivate
     * @return the created post
     */
    public static BPost postPost(int authorId, int recipientId, String subject, String messageBody, boolean isPrivate) {
        ProfileEntity postedFrom = new ProfileLogic().getProfileById(authorId);
        if (postedFrom == null)
            return null;
        ProfileEntity postedTo = new ProfileLogic().getProfileById(recipientId);
        if (postedTo == null)
            return null;

        PostEntity p = new PostLogic().createPost(authorId,recipientId,subject,messageBody,postedTo, postedFrom, isPrivate);
        System.out.println("Post returning from PostLogic: " + p.toString());
        return p != null? new BPost(p.getId(),p.getSubject(),p.getMessageBody(),p.getAuthorName(),p.getRecipientName(),p.getTimestamp(),p.getAuthorId(),p.getRecipientId(),p.isPrivate()) : null;
    }
}
