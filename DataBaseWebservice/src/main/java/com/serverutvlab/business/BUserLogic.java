package com.serverutvlab.business;

import com.serverutvlab.business.BModels.BUser;
import com.serverutvlab.database.DBLayer.DBFacade;

import java.util.List;

/**
 * Created by cj on 2016-11-17.
 */
public class BUserLogic {

    public List<BUser> getAllUsers() {
        return DBFacade.getAllUsers();
    }

    public BUser getUserById(int id) {
        return DBFacade.getUserById(id);
    }

    public boolean authenticateUser(String email, String password) {
        return DBFacade.authenticateUser(email,password);
    }

    public boolean createUser(String email, String password){
        return DBFacade.createNewUser(email, password);
    }

    public List<BUser> getFriendsByUserId(int id) {

        return DBFacade.getFriendsByUserId(id);

    }

    public boolean addFriendToUser(int uId, int fId) {
        return DBFacade.addFriendToUser(uId,fId);
    }
}
