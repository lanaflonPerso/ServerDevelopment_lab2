package com.serverutvlab.database.DBLayer;


import com.serverutvlab.business.BModels.BUser;
import com.serverutvlab.database.DBModels.PostEntity;
import com.serverutvlab.database.DBModels.ProfilEntity;
import com.serverutvlab.database.DBModels.UserEntity;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.util.ArrayList;
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

    static public List<BUser> getAllUsers() {
        List<UserEntity> entities = new UserLogic().getAllUsers();
        List<BUser> result = new ArrayList<BUser>();
        for (UserEntity e: entities)
            result.add(new BUser(e.getId(),e.getEmail(),e.getPassword()));
        return result;
    }

    static public boolean createNewUser(UserEntity user) {
        return new UserLogic().createNewUser(user);
    }

    public static boolean authenticateUser(String e, String p) { return new UserLogic().authenticateUser(e, p); }

    public static UserEntity getUserById(int id) { return new UserLogic().getUserById(id);}
}
