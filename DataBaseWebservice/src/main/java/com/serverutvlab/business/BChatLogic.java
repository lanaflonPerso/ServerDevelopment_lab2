package com.serverutvlab.business;

import com.google.gson.Gson;
import com.serverutvlab.services.SModels.ChatMessageVM;

/**
 * Created by o_0 on 2016-11-22.
 */
public class BChatLogic {


    /**
     * broadcast message
     * @param chatMessageVM
     * @return
     */
    // TODO: check so that they are actually friends that tries to talk
    public static boolean broadcastMessage(ChatMessageVM chatMessageVM) {
        System.out.printf("BChatLogic::broadcastMessage: " + chatMessageVM.toString());
        // send to all frontendservers
        String[] frontendAddresses = FrontendRestLink.getFrontendAddresses();
        String json = new Gson().toJson(chatMessageVM);
        for (String addr : frontendAddresses) {
            FrontendRestLink.doRestPost(addr, "backend/channelService/", "chatMessage", json);
        }

        System.out.printf("BChatLogic::broadcastMessage");
        return true;
    }
}
