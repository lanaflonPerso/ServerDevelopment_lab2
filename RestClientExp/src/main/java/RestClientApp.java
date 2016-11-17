import DataConn.RestBackendLink;

/**
 * Created by o_0 on 2016-11-17.
 */
public class RestClientApp {
    public static void main(String[] args) {
        String users = RestBackendLink.getUsers();
        System.out.println("This is the rest reply: " + users);
    }
}
