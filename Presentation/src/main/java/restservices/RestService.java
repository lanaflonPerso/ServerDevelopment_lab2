package restservices;


import restservices.SModels.SChannelNotification;
import chat.ChatChannelRelay;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by o_0 on 2016-11-14.
 */
@Path("channelService")
public class RestService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getServiceName()
    {
        return "Name: chatService";
    }


    @POST
    @Path("chatMessage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response channelMessage(String messageJson) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
        System.out.println("ChatService::chatMessage incoming: " + messageJson);
        boolean result = new ChatChannelRelay().channelRelay(messageJson);

        resultMap.put("success", result);

        Gson gson = new Gson();
        String response = gson.toJson(resultMap);

        return Response.ok(response).build();
    }

    @POST
    @Path("channelNotification")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response channelNotification(String messageJson) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
        System.out.println("ChatService::sendMessage: " + messageJson);
        SChannelNotification channelInfo = new Gson().fromJson(messageJson, SChannelNotification.class);

//        boolean result = new ChannelRelay().channelRelay(channelInfo.getTargetChannel(),channelInfo.getJsonObject());
        boolean result = new ChannelRelay().channelNotification(channelInfo);

        resultMap.put("success", result);

        Gson gson = new Gson();
        String response = gson.toJson(resultMap);

        return Response.ok(response).build();
    }

}
