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
     *  BUSER LOGIC CALLS
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    static public List<BUser> getAllUsers() {
        List<UserEntity> entities = new UserLogic().getAllUsers();
        List<BUser> result = new ArrayList<BUser>();
        for (UserEntity e: entities)
            result.add(new BUser(e.getId(),e.getEmail(),e.getPassword()));
        return result;
    }

    static public boolean createNewUser(String email, String password) {

        return new UserLogic().createNewUser(email,password);
    }

    public static boolean authenticateUser(String e, String p) {
        return new UserLogic().authenticateUser(e, p);
    }

    public static BUser getUserById(int id) {
        UserEntity user = new UserLogic().getUserById(id);
        return new BUser(user.getId(),user.getEmail(),user.getPassword());
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *  BPROFILE LOGIC CALLS
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    static public BProfile getProfileForUserId(int userId) {
        ProfileEntity profile = new ProfileLogic().getProfileByUserId(userId);
        List<BPost> posts = new ArrayList<BPost>();

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
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *  BPOST LOCIG CALLS
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    static public PostEntity getPostWithId(int id) {
        return null;
    }

}
