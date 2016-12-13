package DBLayer;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by cj on 2016-12-13.
 */
public class DBManager {

    private static volatile DBManager db = null;
    private DataSource dataSource;

    static final String DB_URL = "jdbc:mysql://localhost:3306/";
    static final String USERNAME = "serverutv";
    static final String PASSWORD = "!Qaz2wsx";

    private DataSource newMysqlDataSource() {

        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL(DB_URL);
        ds.setUser(USERNAME);
        ds.setPassword(PASSWORD);

        return ds;
    }

    private DBManager(){
        this.dataSource = newMysqlDataSource();
    }

    /**
     * This is based on the recommendation for the singleton pattern from :
     * https://github.com/iluwatar/java-design-patterns/blob/master/singleton/src/main/java/com/iluwatar/singleton/ThreadSafeDoubleCheckLocking.java
     * Double checking singleton getInstance method.
     * @return the new database managern
     */
    public static DBManager getInstance() {
        DBManager current = db;
        // fast check to see if already created
        if (current == null) {
            // sync so not more then one creates it
            synchronized (DBManager.class) {
                current = db;
                // check if between first check and sync if someone has created it
                if (current == null) {
                    //create it
                    db = current = new DBManager();
                }
            }
        }
        return current;
    }


    public Connection getConnection() throws SQLException {
        Connection connection = this.dataSource.getConnection();
        Statement stmt = connection.createStatement();
        String useShop = "USE CommunityDB;";
        stmt.execute(useShop);
        return connection;
    }


    public GroupDAO getGroupDAO(){
        return new GroupDAO();
    }
    public MessageDAO getMessageDAO(){
        return new MessageDAO();
    }

}
