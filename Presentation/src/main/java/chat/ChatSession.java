package chat;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by o_0 on 2016-11-23.
 */
@ManagedBean
@SessionScoped
public class ChatSession {

    private String userName;
    private String destinationName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        System.out.println("ChatSession::setUserName" + userName);
        this.userName = userName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        System.out.println("ChatSession::setDestinationName" + destinationName);
        this.destinationName = destinationName;
    }

    @PostConstruct
    public void init() {
        this.userName = "";
        this.destinationName = "";
        this.loggedin = false;
    }

    private boolean loggedin;

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }

    @Override
    public String toString() {
        return "ChatSession{" +
                "userName='" + userName + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", loggedin=" + loggedin +
                '}';
    }
}
