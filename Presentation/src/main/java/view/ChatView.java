package view;

import chat.ChatMessageVM;
import chat.ChatSession;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;

/**
 * Created by o_0 on 2016-11-27.
 */
@ManagedBean
@ViewScoped
public class ChatView {

    @ManagedProperty("#{chatSession}")
    private ChatSession chatSession;

    public ChatSession getChatSession() {
        return chatSession;
    }

    public void setChatSession(ChatSession chatSession) {
        this.chatSession = chatSession;
    }

    //private String incomingName;
    private String incomingMsg;

    private String message;
    private String destinationId;


    @PostConstruct
    public void init() {
        this.message = "";
        this.destinationId = "";
        this.incomingMsg = "";
       // this.incomingName = "";
        this.messageBoard = new ArrayList<ChatMessageVM>();

    }

    private ArrayList<ChatMessageVM> messageBoard;

    public void setIncomingMsg(String incomingMsg) {
        this.incomingMsg = incomingMsg;
    }

    public void updateChatBox() {
        if (chatSession.isChatActive() == false) {
            System.out.println("updateChatBox isChatActive == false");
            return;
        }
        if (incomingMsg != null) {
            messageBoard.add(new ChatMessageVM(incomingMsg,chatSession.getDestinationName(),chatSession.getUserName()));
            incomingMsg = null;
            //incomingName = null;
        }
    }

    public ArrayList<ChatMessageVM> getMessageBoard() {
        return messageBoard;
    }

    public void setMessageBoard(ArrayList<ChatMessageVM> messageBoard) {
        this.messageBoard = messageBoard;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDestinationId() {
        return "" + chatSession.getDestinatinoId();
    }

    public void setDestinationId(String destinationId) {
        if (destinationId != null) {
            chatSession.updateDestination(destinationId);
            this.destinationId = destinationId;
        }
    }

    public String getIncomingMsg() {
        return incomingMsg;
    }

    public void startChat() {
        boolean foundUser = chatSession.updateDestination(destinationId);
        System.out.println("chat started: To user: " + chatSession.getDestinationName() + "  from: " + chatSession.getUserName() + " destIdHidden:" + destinationId);

        if (foundUser) {
            RequestContext.getCurrentInstance().execute("PF('subscriber').connect('/" + chatSession.getDestinationName() + "')");
            chatSession.setChatActive(true);
        }else {
            System.out.println("founduser false");
        }
    }

    public void endChat() {
        chatSession.sendChatMessage(chatSession.getUserName() +" has now left the chat");
        chatSession.setChatActive(false);

    }



    public String getChatRoom() {
        return chatSession.getUserName();
    }

    public void sendChatMessage() {
        if (chatSession.isChatActive() == false) {
            return;
        }
        if (message != null) {
            messageBoard.add(new ChatMessageVM(incomingMsg,chatSession.getUserName(),chatSession.getDestinationName()));
            chatSession.sendChatMessage(message);
            message = null;
        }
    }

    public void acceptChatRequest() {
        System.out.println("acceptChatRequest: destinationId = " + destinationId);

        boolean foundUser = chatSession.updateDestination(destinationId);

    }

    public void sendChatRequest(int destId) {
        this.destinationId = "" + destId;
        chatSession.setDestinatinoId(destId);
        boolean chat = chatSession.sendChatRequest( "chat");
//        if (chat) {
//            startChat();
//        }

    }
}
