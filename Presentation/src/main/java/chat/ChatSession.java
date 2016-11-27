package chat;

import backend.RestBackendLink;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by o_0 on 2016-11-23.
 */
@ManagedBean
@SessionScoped
public class ChatSession {
    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();
    private int destinatinoId;
    private String destinationName;
    private boolean chatActive;

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

    public boolean sendChatMessage(int userId,String message) {
        if (chatActive == false) {
            System.out.println("sendChatMessage:chat is not active");
            return false;
        }
        boolean b = RestBackendLink.sendChatMessage(new ChatMessageVM(message, "" + userId, "" + destinatinoId));
        return b;
    }

}
