package view;

import account.Account;
import backend.BackendFacade;
import chat.ChatSession;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by o_0 on 2016-11-27.
 */
@ManagedBean
@ViewScoped
public class ChatView {
    @ManagedProperty("#{account}")
    private Account userAccount;

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    @ManagedProperty("#{chatSession}")
    private ChatSession chatSession;

    public ChatSession getChatSession() {
        return chatSession;
    }

    public void setChatSession(ChatSession chatSession) {
        this.chatSession = chatSession;
    }

    private String message;
    private String destinationId;


    @PostConstruct
    public void init() {
        this.message = "";
        this.destinationId = "";
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public void startChat() {
        System.out.println("chat started: To user: " + chatSession.getDestinationName() + "  from: " + userAccount.getUsername() + " destIdHidden:" + destinationId);
    }

    public void sendChatRequest(int destId) {
        this.destinationId = "" + destId;
        chatSession.setDestinatinoId(destId);
        chatSession.sendChatRequest(userAccount.getUserId(),"chat");

    }
}
