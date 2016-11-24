package com.serverutvlab.business;

import com.serverutvlab.business.BModels.BUser;
import com.serverutvlab.database.DBLayer.DBFacade;

import java.util.ArrayList;
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

    public BUser authenticateUser(String email, String password) {
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

    public boolean removeFriend(int uId, int fId) {
        return DBFacade.removeFriend(uId,fId);
    }

    public List<BUser> getNonFriendsByUser(int userId) {
        List<BUser> allUsers = DBFacade.getAllUsers();
        System.out.println("All users before: " + allUsers );
        List<BUser> friends = DBFacade.getFriendsByUserId(userId);
        List<BUser> results = new ArrayList<BUser>();
        for (BUser b: allUsers) {
            for (BUser f : friends) {
                if (b.getId() != f.getId() && b.getId() != userId) {
                    results.add(b);
                }
            }
        }

        System.out.println("All users after: " + results);

        return results;
    }
}
