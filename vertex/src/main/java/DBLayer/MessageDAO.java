package DBLayer;

import DBLayer.DBModels.DBMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cj on 2016-12-13.
 */


public class MessageDAO {
    private static final String SQL_GET_ALL_USERS = "SELECT * FROM T_USER";
    private static final String SQL_DELETE_USER = "DELETE FROM T_USER WHERE userID = ?";
    private static final String SQL_UPDATE_USER = "UPDATE T_USER SET userEmail = ?,userPassword = ?,userType = ? WHERE userID = ?";


    private static final String MESSAGE_ID = "id";
    private static final String FROM_ID = "fromId";
    private static final String TO_ID = "toId";
    private static final String TEXT = "text";
    private static final String TIMESTAMP = "timestamp";
    private static final String FROM_NAME = "fromName";
    private static final String TO_NAME = "toName";


    private static final String SQL_INSERT_MESSAGE_TO_USER = "INSERT INTO Message (fromId,toId,text,fromName,toName) VALUES (?,?,?,?,?)";
    private static final String SQL_INSERT_MESSAGE_TO_GROUP = "INSERT INTO Message (fromId,groupId,text,fromName,toName) VALUES (?,?,?,?,?)";
    private static final String SQL_GET_MESSAGES_BY_GROUP = "SELECT * FROM MESSAGE WHERE groupId = ?"; // toId = groupId
    private static final String SQL_GET_MESSAGES_BETWEEN_USERS = "SELECT * FROM MESSAGE WHERE fromId = ? and toId = ?";


    public void insertMessageToUser(DBMessage message) {
        PreparedStatement ps = null;
        Connection dbConn = null;
        System.out.println("insertMsgToUser: " + message.toString());
        try {
            dbConn = DBManager.getInstance().getConnection();
            ps = dbConn.prepareStatement(SQL_INSERT_MESSAGE_TO_USER);
            ps.setInt(1, message.getFromId());
            ps.setInt(2, message.getToId());
            ps.setString(3, message.getText());
            ps.setString(4, message.getFromName());
            ps.setString(5, message.getToName());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (dbConn != null) {
                try {
                    dbConn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void insertMessageToGroup(DBMessage message) {
        PreparedStatement ps = null;
        Connection dbConn = null;
        System.out.println("insertMsgToGroup: " + message.toString());
        try {
            dbConn = DBManager.getInstance().getConnection();
            ps = dbConn.prepareStatement(SQL_INSERT_MESSAGE_TO_GROUP);
            ps.setInt(1, message.getFromId());
            ps.setInt(2, message.getGroupId());
            ps.setString(3, message.getText());
            ps.setString(4, message.getFromName());
            ps.setString(5, message.getToName());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (dbConn != null) {
                try {
                    dbConn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<DBMessage> getMessagesByGroup(int groupId) {
        List<DBMessage> messages = new ArrayList<>();
        Connection dbConn = null;
        System.out.println("getting messages from group");
        try {
            dbConn = DBManager.getInstance().getConnection();
            PreparedStatement ps = dbConn.prepareStatement(SQL_GET_MESSAGES_BY_GROUP);
            ps.setInt(1, groupId);

            ResultSet resultSet = ps.executeQuery();
            System.out.println("Number of results: " + resultSet.getFetchSize());

            messages = getMessagesFromRS(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (dbConn != null) {
                try {
                    dbConn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return messages;
    }


    public List<DBMessage> getMessagesBetweenUsers(int fromId, int toId) {
        List<DBMessage> messages = new ArrayList<>();
        Connection dbConn = null;
        System.out.println("getting messages between users");

        try {
            dbConn = DBManager.getInstance().getConnection();
            PreparedStatement ps = dbConn.prepareStatement(SQL_GET_MESSAGES_BETWEEN_USERS);
            ps.setInt(1, fromId);
            ps.setInt(2, toId);

            ResultSet resultSet = ps.executeQuery();
            System.out.println("Number of results: " + resultSet.getFetchSize());

            messages.addAll(getMessagesFromRS(resultSet));

            PreparedStatement ps2 = dbConn.prepareStatement(SQL_GET_MESSAGES_BETWEEN_USERS);
            ps2.setInt(2, fromId);
            ps2.setInt(1, toId);

            ResultSet resultSet2 = ps2.executeQuery();

            messages.addAll(getMessagesFromRS(resultSet2));

            Collections.sort(messages);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (dbConn != null) {
                try {
                    dbConn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return messages;
    }


    public List<DBMessage> getMessagesFromRS(ResultSet rs) throws SQLException {
        List<DBMessage> messages = new ArrayList<>();

        while (rs.next()) {
            DBMessage msg = new DBMessage();

            msg.setId(rs.getInt(MESSAGE_ID));
            msg.setFromId(rs.getInt(FROM_ID));
            msg.setToId(rs.getInt(TO_ID));
            msg.setText(rs.getString(TEXT));
            msg.setTimestamp(rs.getTimestamp(TIMESTAMP));
            msg.setFromName(rs.getString(FROM_NAME));
            msg.setToName(rs.getString(TO_NAME));

            if (!messages.contains(msg))
                messages.add(msg);
        }

        return messages;
    }
}
