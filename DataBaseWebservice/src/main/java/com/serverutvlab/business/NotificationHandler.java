package com.serverutvlab.business;

import com.google.gson.Gson;
import com.serverutvlab.services.SModels.SNotification;

/**
 * Created by o_0 on 2016-11-27.
 */
public class NotificationHandler {
    public static void newFriendNotification(int friendId,String message) {
        String s = new Gson().toJson(new SNotification("message"));
        FrontendRestLink.sendNotification(friendId,"newfriend",s);

    }

    public static void chatRequestNotification(int senderId,int targetId) {
        //String s = new Gson().toJson(new SNotification(""+senderId));
        String s = "" + senderId;
        FrontendRestLink.sendNotification(targetId,"chatRequest",s);
    }
}
