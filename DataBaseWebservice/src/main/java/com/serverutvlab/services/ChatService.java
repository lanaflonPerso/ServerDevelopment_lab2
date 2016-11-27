package com.serverutvlab.services;

import com.google.gson.Gson;
import com.serverutvlab.business.BFacade;
import com.serverutvlab.services.SModels.ChatMessageVM;
import com.serverutvlab.services.SModels.SUser;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by o_0 on 2016-11-14.
 */
@Path("chatService")
public class ChatService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getServiceName()
    {
        return "Name: chatService";
    }

    /**
     * sends a message to another user
     * @param messageJson
     * @return
     */
    @POST
    @Path("sendmessage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response sendMessage(String messageJson) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
        System.out.println("ChatService::sendMessage: " + messageJson);
        ChatMessageVM chatMessageVM = new Gson().fromJson(messageJson, ChatMessageVM.class);
        //BFacade.registerUser(email,password);
        boolean result = BFacade.sendMessage(chatMessageVM);

        resultMap.put("success", result);

        Gson gson = new Gson();
        String response = gson.toJson(resultMap);

        return Response.ok(response).build();
    }


    @POST
    @Path("chatRequest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response chatRequest(String messageJson) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
        System.out.println("ChatService::chatRequest: " + messageJson);
        ChatMessageVM chatMessageVM = new Gson().fromJson(messageJson, ChatMessageVM.class);
        //BFacade.registerUser(email,password);
        boolean result = BFacade.sendMessage(chatMessageVM);

        resultMap.put("success", result);

        Gson gson = new Gson();
        String response = gson.toJson(resultMap);

        return Response.ok(response).build();
    }




}
