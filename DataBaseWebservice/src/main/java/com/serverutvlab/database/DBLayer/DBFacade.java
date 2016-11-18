package com.serverutvlab.database.DBLayer;


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
        //new ProfileLogic().getProfileByUserId(userId);
        return null;
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
