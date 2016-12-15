package DBLayer;

import DBLayer.DBModels.DBGroup;
import DBLayer.DBModels.DBMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by cj on 2016-12-13.
 */
public class GroupDAO {

    private static final String GROUP_ID = "id";
    private static final String NAME = "name";

    private static final String SQL_INSERT_GROUP = "INSERT INTO CommunityDB.Group (name) VALUES (?)";
    private static final String SQL_INSERT_USER_TO_GROUP = "INSERT INTO CommunityDB.GroupUserId (groupId, userId) VALUES (?,?)";
    private static final String SQL_GET_GROUPS = "SELECT * FROM CommunityDB.Group";
    private static final String SQL_GET_GROUP = "SELECT * FROM CommunityDB.Group WHERE id = ?";
    private static final String SQL_GET_USERS_BY_GROUP = "SELECT * FROM CommunityDB.GroupUserId WHERE groupId = ?";



    public int insertGroup(String name) {
        PreparedStatement ps = null;
        Connection dbConn = null;
        int groupId = 0;
        System.out.println("Creating group: " + name);
        try {
            dbConn = DBManager.getInstance().getConnection();
            ps = dbConn.prepareStatement(SQL_INSERT_GROUP);
            ps.setString(1, name);
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            groupId = keys.getInt(1);


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
        return groupId;
    }


    public List<Integer> getUserIdsForGroups(int groupId) {
        List<Integer>  userIds = new ArrayList<>();
        Connection dbConn = null;

        try {
            dbConn = DBManager.getInstance().getConnection();
            PreparedStatement ps = dbConn.prepareStatement(SQL_GET_USERS_BY_GROUP);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                userIds.add(rs.getInt("userId"));
            }


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
        return userIds;
    }


    public DBGroup getGroupById(int groupId) {
        List<DBGroup> groups = new ArrayList<>();
        Connection dbConn = null;
        System.out.println("getting groups");

        try {
            dbConn = DBManager.getInstance().getConnection();
            PreparedStatement ps = dbConn.prepareStatement(SQL_GET_GROUP);
            ps.setInt(1,groupId);

            ResultSet resultSet = ps.executeQuery();
            System.out.println("Number of results: " + resultSet.getFetchSize());

            groups = getGroupsFromRS(resultSet);

            if (groups.size() == 1){
                return groups.get(0);
            }


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
        return null;
    }



    public List<DBGroup> getGroups() {
        List<DBGroup> groups = new ArrayList<>();
        Connection dbConn = null;
        System.out.println("getting groups");

        try {
            dbConn = DBManager.getInstance().getConnection();
            PreparedStatement ps = dbConn.prepareStatement(SQL_GET_GROUPS);
            ResultSet resultSet = ps.executeQuery();
            System.out.println("Number of results: " + resultSet.getFetchSize());

            groups = getGroupsFromRS(resultSet);


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
        return groups;
    }




    public List<DBGroup> getGroupsFromRS(ResultSet rs) throws SQLException {
        List<DBGroup> groups = new ArrayList<>();

        while (rs.next()) {
            DBGroup grp = new DBGroup();

            grp.setId(rs.getInt(GROUP_ID));
            grp.setName(rs.getString(NAME));

            groups.add(grp);
        }

        return groups;
    }

    public void addUserToGroup(Integer groupId, Integer userId) {
        PreparedStatement ps = null;
        Connection dbConn = null;
        try {
            dbConn = DBManager.getInstance().getConnection();
            ps = dbConn.prepareStatement(SQL_INSERT_USER_TO_GROUP);
            ps.setInt(1, groupId);
            ps.setInt(2, userId);
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

}
