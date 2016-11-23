package chat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;


import backend.RestBackendLink;
import org.primefaces.context.RequestContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;


/**
 * Created by o_0 on 2016-11-21.
 */
@ManagedBean
@ViewScoped
public class ChatWindow implements Serializable {
       private final EventBus eventBus = EventBusFactory.getDefault().eventBus();

    private static final String CHANNEL = "/{room}/";
    private String theMessage;
    private boolean isLoggedin = false;

    @ManagedProperty("#{chatSession}")
    private ChatSession chatSession;

    public ChatSession getChatSession() {
        return chatSession;
    }

    public void setChatSession(ChatSession chatSession) {
        this.chatSession = chatSession;
    }

    private String currentUser;
    private String toUser;

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getTheMessage() {
        return theMessage;
    }

    public boolean isLoggedin() {
        return isLoggedin;
    }

    public void setTheMessage(String theMessage) {
        this.theMessage = theMessage;
    }
    private  void startChat() {
        System.out.println("ChatWindow::startChat");
        RequestContext.getCurrentInstance().execute("PF('subscriber').connect('/" + chatSession.getUserName() + "')");
        isLoggedin = true;
    }

    public void login() {
        System.out.println("ChatWindow::login");
        System.out.println("ChatWindow::login prev chatsession: " + chatSession.toString());
        currentUser = (currentUser == null) ? "unknownUser" : currentUser;
        currentUser = (toUser == null) ? "unknowntoUser" : toUser;
        chatSession.setUserName(currentUser);
        chatSession.setDestinationName(toUser);
        System.out.println("ChatWindow::login: " + chatSession);
        // this starts the socket connection command in the page,
        startChat();
       // RequestContext.getCurrentInstance().execute("PF('subscriber').connect('/" + chatSession.getUserName() + "')");
//        isLoggedin = true;

    }

    public ArrayList<String> getTextLists() {
        return textLists;
    }

    public void setTextLists(ArrayList<String> textLists) {
        this.textLists = textLists;
    }

    private ArrayList<String> textLists = new ArrayList<String>();

    public void postMsg() {
        if (chatSession.isLoggedin() == false) {
            System.out.println("Not loggedin");
            return;
        }
        if(isLoggedin == false) {
            startChat();
        }
        System.out.println("postMsg: " + theMessage);
        textLists.add(theMessage);
//        resetEventBus();
        if (eventBus == null) {
            System.out.println("postMsg failed eventbus == null");
        }else {
            toUser = (toUser == null) ? "unknownUser" : toUser;
//            RestBackendLink.sendChatMessage(new ChatMessageVM(theMessage,currentUser,toUser));
            RestBackendLink.sendChatMessage(new ChatMessageVM(theMessage,chatSession.getUserName(),chatSession.getDestinationName()));
        }
        //FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get();
        //eventBus.publish()
    }
}
