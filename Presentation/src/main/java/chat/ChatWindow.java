package chat;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;


import org.primefaces.context.RequestContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;


/**
 * Created by o_0 on 2016-11-21.
 */
@ManagedBean
@ViewScoped
public class ChatWindow implements Serializable{
//    private final PushContext pushContext = PushContextFactory.getDefault().getPushContext();
       private final EventBus eventBus = EventBusFactory.getDefault().eventBus();
//    private EventBus eventBus;// = EventBusFactory.getDefault().eventBus();
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
        System.out.println("ChatWindow::login");
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
        System.out.println("postMsg: " + theMessage);
        textLists.add(theMessage);
//        resetEventBus();
        if (eventBus == null) {
            System.out.println("postMsg failed eventbus == null");
        }else {
//            eventBus.publish(CHANNEL + "", "message text");
            eventBus.publish(CHANNEL + "name22", new ChatMessage(theMessage,"from","to"));
        }
        //FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get();
        //eventBus.publish()
    }
}
