package com.serverutvlab.database.DBTest;

import com.serverutvlab.database.DBLayer.UserLogic;
import com.serverutvlab.database.DBModels.UserEntity;

/**
 * Created by o_0 on 2016-11-23.
 */
public class DBTest {
    public static void main(String[] args) {
        //UserEntity newAccount = new UserLogic().createNewAccount("maile2", "pass");
        boolean b = true;
        b = b && new UserLogic().addFriendToUser(1, 5);
        b = b && new UserLogic().addFriendToUser(1, 3);
        b = b && new UserLogic().addFriendToUser(1, 7);
        b = b && new UserLogic().addFriendToUser(3, 7);
        b = b && new UserLogic().addFriendToUser(3, 9);
        b = b && new UserLogic().addFriendToUser(3, 5);
        b = b && new UserLogic().addFriendToUser(7, 9);
        System.out.println("User enti: " + b);
        //b = new UserLogic().addFriendToUser(9, 5);

        System.out.println("User enti: " + b);

    }
}
