import DBLayer.DBFacade;
import DBLayer.DBManager;
import DBLayer.DBModels.DBGroup;
import DBLayer.DBModels.DBMessage;
import com.google.gson.Gson;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

/**
 * Created by cj on 2016-12-14.
 */
public class Handlers {



    public static void handleSendMessageToUser(RoutingContext routingContext){

        JsonObject obj = routingContext.getBodyAsJson();

        int fromId = obj.getInteger("fromId");
        int toId = obj.getInteger("toId");
        String text = obj.getString("text");
        String fromName = obj.getString("fromName");
        String toName = obj.getString("toName");

        DBMessage msg = new DBMessage();
        msg.setFromId(fromId);
        msg.setToId(toId);
        msg.setText(text);
        msg.setFromName(fromName);
        msg.setToName(toName);

        DBFacade.insertMessageToUser(msg);

        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "text/html")
                .end("Message To User Sent: " + obj.toString());
    }


    public static void handleSendMessageToGroup(RoutingContext routingContext){

        JsonObject obj = routingContext.getBodyAsJson();

        int fromId = obj.getInteger("fromId");
        int groupId = obj.getInteger("groupId");
        String text = obj.getString("text");
        String fromName = obj.getString("fromName");

        DBMessage msg = new DBMessage();
        msg.setFromId(fromId);
        msg.setGroupId(groupId);
        msg.setText(text);
        msg.setFromName(fromName);

        DBFacade.insertMessageToGroup(msg);

        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "text/html")
                .end("Message To Group Sent: " + obj.toString());
    }



    public static void handleGetMessagesByGroup(RoutingContext routingContext){

        int groupId = Integer.parseInt(routingContext.request().getParam("groupId"));
        System.out.println("Id from param: " + groupId);



        List<DBMessage> messages = DBFacade.getMessagesByGroup(groupId);

        Gson gson = new Gson();
        String response = gson.toJson(messages);


        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "text/html")
                .end("Messages: " + response);
    }

    public static void handleGetMessagesBetweenUsers(RoutingContext routingContext){

        int fromId = Integer.parseInt(routingContext.request().getParam("fromId"));
        int toId = Integer.parseInt(routingContext.request().getParam("toId"));
        System.out.println("FromId from param: " + fromId);
        System.out.println("ToId from param: " + toId);

        List<DBMessage> messages = DBFacade.getMessagesBetweenUsers(fromId,toId);

        Gson gson = new Gson();
        String response = gson.toJson(messages);


        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "text/html")
                .end("Messages: " + response);
    }


    public static void handleGetGroups(RoutingContext routingContext){


        List<DBGroup> groups = DBFacade.getGroups();

        Gson gson = new Gson();
        String response = gson.toJson(groups);


        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "text/html")
                .end("Messages: " + response);
    }


}
