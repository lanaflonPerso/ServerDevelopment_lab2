package chat;

import org.primefaces.context.RequestContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;

/**
 * Created by o_0 on 2016-11-21.
 */
@ManagedBean
@ViewScoped
public class ChatWindow {
    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();

    private static final String CHANNEL = "/{room}/";
    private String theMessage;
    private boolean isLoggedin = false;

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
        // this starts the socket connection command in the page,
        RequestContext.getCurrentInstance().execute("PF('subscriber').connect('/" + "name22" + "')");
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
        eventBus.publish(CHANNEL + "u22", "message text");
        //FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get();
        //eventBus.publish()
    }
}
