import DBLayer.DBFacade;
import DBLayer.DBManager;
import DBLayer.DBModels.DBGroup;
import DBLayer.DBModels.DBMessage;
import com.google.gson.Gson;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

/**
 * Created by cj on 2016-12-14.
 */
public class Handlers {



    static JsonObject handleSendMessageToUser(JsonObject obj){


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

        return obj;
    }


    static JsonObject handleSendMessageToGroup(JsonObject obj){


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

        return obj;
    }



    static JsonObject handleGetMessagesByGroup(JsonObject obj){

        int groupId = obj.getInteger("groupId");
        System.out.println("Id from param: " + groupId);



        List<DBMessage> messages = DBFacade.getMessagesByGroup(groupId);

        obj.put("response",messages);

        return obj;

    }

    static JsonObject handleGetMessagesBetweenUsers(JsonObject obj){

        int fromId = obj.getInteger("fromId");
        int toId = obj.getInteger("toId");

        List<DBMessage> messages = DBFacade.getMessagesBetweenUsers(fromId,toId);

        obj.put("response",messages);

        return obj;
    }


    static JsonObject handleGetGroups(JsonObject obj){

        List<DBGroup> groups = DBFacade.getGroups();

        obj.put("response",groups);

        return obj;



    }
}
