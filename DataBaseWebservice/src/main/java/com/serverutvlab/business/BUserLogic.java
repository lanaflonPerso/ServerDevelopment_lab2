package com.serverutvlab.business;

import com.google.gson.Gson;
import com.serverutvlab.business.BModels.BProfile;
import com.serverutvlab.business.BModels.BUser;
import com.serverutvlab.database.DBLayer.DBFacade;
import com.serverutvlab.services.SModels.SNotification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by cj on 2016-11-17.
 */
public class BUserLogic {

    /**
     * get all users
     * @return all users
     */
    public List<BUser> getAllUsers() {
        return DBFacade.getAllUsers();
    }

    public BUser getUserById(int id) {
        BUser user = DBFacade.getUserById(id);
        if (user == null)
            return null;
        BProfile profile = DBFacade.getProfileForUserId(id);
        if (profile == null)
            return null;
        user.setProfileId(profile.getId());
        return user;
    }

    /**
     * authentiate user
     * @param email
     * @param password
     * @return user
     */
    public BUser authenticateUser(String email, String password) {
        BUser user = DBFacade.authenticateUser(email,password);
        if (user == null)
            return null;
        BProfile profile = DBFacade.getProfileForUserId(user.getId());
        if (profile == null)
            return null;
        user.setProfileId(profile.getId());
        return user;

    }

    /**
     * create user
     * @param email
     * @param password
     * @return
     */
    public BUser createUser(String email, String password){

        BUser user = DBFacade.createNewUser(email, password);
        if (user == null)
            return null;
        BProfile profile = DBFacade.getProfileForUserId(user.getId());
        if (profile == null)
            return null;
        user.setProfileId(profile.getId());
        return user;
    }

    /**
     * get friends by user
     * @param id
     * @return list of friends
     */
    public List<BUser> getFriendsByUserId(int id) {

        return DBFacade.getFriendsByUserId(id);

    }

    /**
     * adding friendship
     * @param uId
     * @param fId
     * @return
     */
    public boolean addFriendToUser(int uId, int fId) {
        boolean success = DBFacade.addFriendToUser(uId,fId);
        if (success) {
            NotificationHandler.newFriendNotification(fId,"friend");
//            String s = new Gson().toJson(new SNotification("test string"));
//            FrontendRestLink.sendNotification(fId,s);
        }
        return success;
    }
    /**
     * removing friendship
     * @param uId
     * @param fId
     * @return
     */
    public boolean removeFriend(int uId, int fId) {
        return DBFacade.removeFriend(uId,fId);
    }

    /**
     * get non friends
     * @param userId
     * @return
     */
    public List<BUser> getNonFriendsByUser(int userId) {
        BUser userById = DBFacade.getUserById(userId);
        if (userById == null) {
            return null;
        }
        List<BUser> allUsers = DBFacade.getAllUsers();
        if (allUsers == null)
            return null;

        System.out.println("All users before: " + allUsers );
        List<BUser> friends = DBFacade.getFriendsByUserId(userId);
        if (friends == null)
            return null;

        HashSet<BUser> uset = new HashSet<BUser>();
        uset.addAll(allUsers);
        uset.removeAll(friends);
        uset.remove(userById);
        List<BUser> results = new ArrayList<BUser>();
        results.addAll(uset);


//        List<BUser> results = new ArrayList<BUser>();
//        for (BUser b: allUsers) {
//            for (BUser f : friends) {
//                if (b.getId() != f.getId() && b.getId() != userId) {
//                    results.add(b);
//                }
//            }
//        }

        System.out.println("All users after: " + results);

        return results;
    }
}
