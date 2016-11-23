package chat;

import javax.faces.bean.ManagedBean;
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

    public void login() {
        System.out.println("ChatWindow::login");
        currentUser = (currentUser == null) ? "unknownUser" : currentUser;
        // this starts the socket connection command in the page,
        RequestContext.getCurrentInstance().execute("PF('subscriber').connect('/" + currentUser + "')");
        isLoggedin = true;

    }

    public ArrayList<String> getTextLists() {
        return textLists;
    }

    public void setTextLists(ArrayList<String> textLists) {
        this.textLists = textLists;
    }

    private ArrayList<String> textLists = new ArrayList<String>();

    public void postMsg() {
        System.out.println("postMsg: " + theMessage);
        textLists.add(theMessage);
//        resetEventBus();
        if (eventBus == null) {
            System.out.println("postMsg failed eventbus == null");
        }else {
            toUser = (toUser == null) ? "unknownUser" : toUser;
//            eventBus.publish(CHANNEL + "", "message text");
            //eventBus.publish(CHANNEL + toUser, new ChatMessageVM(theMessage,currentUser,toUser));
            RestBackendLink.sendChatMessage(new ChatMessageVM(theMessage,currentUser,toUser));
        }
        //FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get();
        //eventBus.publish()
    }
}
