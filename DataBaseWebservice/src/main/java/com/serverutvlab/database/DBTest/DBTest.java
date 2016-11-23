package com.serverutvlab.database.DBTest;

import com.serverutvlab.database.DBLayer.UserLogic;
import com.serverutvlab.database.DBModels.UserEntity;

/**
 * Created by o_0 on 2016-11-23.
 */
public class DBTest {
    public static void main(String[] args) {
        //UserEntity newAccount = new UserLogic().createNewAccount("maile2", "pass");
        boolean b = new UserLogic().addFriendToUser(1, 3);

        System.out.println("User enti: " + b);

    }
}
