package DBLayer;

import DBLayer.DBModels.DBGroup;
import DBLayer.DBModels.DBMessage;

import java.util.List;

/**
 * Created by cj on 2016-12-13.
 */
public class DBFacade {


    public static void insertMessageToUser(DBMessage message) {
        DBManager.getInstance().getMessageDAO().insertMessageToUser(message);

    }

    public static void insertMessageToGroup(DBMessage message) {
        DBManager.getInstance().getMessageDAO().insertMessageToGroup(message);
    }

    public static List<DBMessage> getMessagesByGroup(int groupId) {
        return DBManager.getInstance().getMessageDAO().getMessagesByGroup(groupId);

    }


    public static List<DBMessage> getMessagesBetweenUsers(int fromId, int toId) {
        return DBManager.getInstance().getMessageDAO().getMessagesBetweenUsers(fromId,toId);
    }


    public static void insertGroup(String name) {
        DBManager.getInstance().getGroupDAO().insertGroup(name);
    }


    public static DBGroup getGroupById(int groupId) {

        return DBManager.getInstance().getGroupDAO().getGroupById(groupId);
    }


    public static List<DBGroup> getGroups() {
        return DBManager.getInstance().getGroupDAO().getGroups();
    }


}
