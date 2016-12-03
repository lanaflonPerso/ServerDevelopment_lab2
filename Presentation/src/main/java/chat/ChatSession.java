package chat;

import account.Account;
import restservices.BackendFacade;
import restservices.RestBackendLink;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import viewmodel.UserVM;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by o_0 on 2016-11-23.
 */
@ManagedBean
@ViewScoped
public class ChatSession {
    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();
//    private String userName;
    private int destinatinoId;
    private String destinationName;
    private boolean chatActive;

    @ManagedProperty("#{account}")
    private Account userAccount;

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }


    public String getUserName() {
        return userAccount.getUsername();
    }


    public int getDestinatinoId() {
        return destinatinoId;
    }

    public void setDestinatinoId(int destinatinoId) {
        this.destinatinoId = destinatinoId;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public boolean isChatActive() {
        return chatActive;
    }

    public void setChatActive(boolean chatActive) {
        this.chatActive = chatActive;
    }

    @PostConstruct
    public void init() {
        this.chatActive = false;
    }

    public boolean sendChatMessage(String message) {
        if (chatActive == false) {
            System.out.println("sendChatMessage:chat is not active");
            return false;
        }
        System.out.println("sendChatMessage");
//        boolean b = RestBackendLink.sendChatMessage(new ChatMessageVM(message, "" + userId, "" + destinatinoId));
        boolean b = RestBackendLink.sendChatMessage(new ChatMessageVM(message, userAccount.getUsername(), destinationName));
        return b;
    }

    public boolean sendChatRequest(String message) {
        System.out.println("sendChatRequest");
        boolean b = RestBackendLink.sendChatRequest(new ChatMessageVM(message, "" + userAccount.getUserId(), "" + destinatinoId));
//        boolean b = RestBackendLink.sendChatRequest(new ChatMessageVM(message, userName, destinationName));
        if (b) {
            chatActive = true;
        }
        return b;
    }

    public boolean updateDestination(String destinationUserId) {
        int destId = -1;
        if (destinationUserId == null) {
            System.out.println("updateDestination: is destinationUserId null");
            return false;
        }
        try {
            destId = Integer.parseInt(destinationUserId);
        } catch (NumberFormatException ex) {
            System.out.println("updateDestination:NumberFormatException");
            return false;
        }
        if (destId < 0) {
            System.out.println("updateDestination:userId < 0");
            return false;
        }

        UserVM user = BackendFacade.getUser(destId);
        if (user != null) {
            this.destinatinoId = user.getUserId();
            this.destinationName = user.getUsername();
            System.out.printf("Chatsession:updateDestination: ok" );
            return true;
        }else {
            System.out.println("updateDestination:UserVM user == null");
        }
        return false;
    }
}
