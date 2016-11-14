package com.serverutvlab.database.DBLayer;


import com.serverutvlab.database.DBModels.PostEntity;
import com.serverutvlab.database.DBModels.ProfilEntity;
import com.serverutvlab.database.DBModels.UserEntity;

import java.util.List;

/**
 * Created by o_0 on 2016-11-10.
 */
public class DBFacade {
    static public PostEntity getPostWithId(int id) {
        return null;
    }

    static public ProfilEntity getProfileForUserId(int userId) {
        return null;
    }

    static public List<UserEntity> getAllUsers() {
        return new UserLogic().getAllUsers();
    }

    static public boolean createNewUser(UserEntity user) {
        return new UserLogic().createNewUser(user);
    }
}
