package com.serverutvlab.business;

import com.serverutvlab.business.BModels.BProfile;
import com.serverutvlab.database.DBLayer.DBFacade;

/**
 * Created by cj on 2016-11-18.
 */
public class BProfileLogic {
    public BProfile getProfileForUser(int userId) {
        return DBFacade.getProfileForUserId(userId);
    }

    public boolean updateProfile(int userId, String username, String info, int relationshipStatus, int age) {
        return DBFacade.updateProfile(userId,username, info,relationshipStatus,age);
    }
}
