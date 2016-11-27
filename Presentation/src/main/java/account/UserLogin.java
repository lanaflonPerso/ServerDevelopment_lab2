package account;

import backend.BackendFacade;
import chat.ChatSession;
import navigationcontroller.NavigationController;
import viewmodel.UserVM;

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

    @ManagedProperty("#{account}")
    private Account userAccount;

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

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
        UserVM userVM = BackendFacade.loginUser(userName, password);
        if (userVM != null) {
            chatSession.setUserName(userName);
            chatSession.setDestinationName(userName);
            chatSession.setLoggedin(true);

            userAccount.fillAccount(userVM);
//            userAccount.setUserId(userVM.getUserId());
//            userAccount.setUsername(userVM.getUsername());
//            userAccount.setProfileId(0);    // TODO: set profile id
//
            userAccount.setLoggedin(true);
            System.out.println("user have loggedin: " + userAccount.toString());

        }else {
            System.out.println("user failed to loggedin: ");
        }
        System.out.println("chat have loggedin: " + chatSession.toString());
        return true;
    }

    public void logout() {
        if (userAccount != null && userAccount.isLoggedin() == false) {
            userAccount.setUserId(-1);
            setUserName("");
            userAccount.setLoggedin(false);
        } else {
            System.out.println("user already loggedout");
        }
    }
}
