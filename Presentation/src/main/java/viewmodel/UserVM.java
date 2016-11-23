package viewmodel;

/**
 * Created by o_0 on 2016-11-23.
 */
public class UserVM {
    private int userId;
    private String username;

    public UserVM(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
