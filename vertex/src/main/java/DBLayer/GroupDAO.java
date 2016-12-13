package DBLayer;

import DBLayer.DBModels.DBGroup;
import DBLayer.DBModels.DBMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cj on 2016-12-13.
 */
public class GroupDAO {

    private static final String GROUP_ID = "id";
    private static final String NAME = "name";

    private static final String SQL_INSERT_GROUP = "INSERT INTO CommunityDB.Group (name) VALUES (?)";
    private static final String SQL_GET_GROUPS = "SELECT * FROM CommunityDB.Group";
    private static final String SQL_GET_GROUP = "SELECT * FROM CommunityDB.Group WHERE id = ?";



    public void insertGroup(String name) {
        PreparedStatement ps = null;
        Connection dbConn = null;
        System.out.println("Creating group: " + name);
        try {
            dbConn = DBManager.getInstance().getConnection();
            ps = dbConn.prepareStatement(SQL_INSERT_GROUP);
            ps.setString(1, name);
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
}
