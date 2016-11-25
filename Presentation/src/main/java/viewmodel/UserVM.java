package viewmodel;

/**
 * Created by o_0 on 2016-11-23.
 */
public class UserVM {
    private int userId;
    private String username;
    private int profileId;

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public UserVM(int userId, String username,int profileId) {
        this.userId = userId;
        this.username = username;
        this.profileId = profileId;
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
