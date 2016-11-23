package navigationcontroller;

import backend.BackendFacade;
import chat.ChatSession;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 * Created by o_0 on 2016-11-23.
 */
@ManagedBean
@SessionScoped
public class UserLogin {
    @ManagedProperty("#{chatSession}")
    private ChatSession chatSession;

    public ChatSession getChatSession() {
        return chatSession;
    }

    public void setChatSession(ChatSession chatSession) {
        this.chatSession = chatSession;
    }

    private String userName = null;
    private String password = null;
    @PostConstruct
    public void init() {
        this.userName = null;
        this.password = null;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean login() {
        // TODO: login into the webpage
        System.out.println("user: " + userName + " pass: " + password);
        boolean success = BackendFacade.loginUser(userName, password);
        if (success) {
            chatSession.setUserName(userName);
            chatSession.setDestinationName(userName);
            chatSession.setLoggedin(true);
        }
        System.out.println("user have loggedin: " + chatSession.toString());
        return true;
    }
}
