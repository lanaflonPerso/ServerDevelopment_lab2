package chat;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;

/**
 * Created by o_0 on 2016-11-21.
 */
@ManagedBean
@ViewScoped
public class ChatWindow {
    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();

//    private static final String CHANNEL = "/{room}"/;

    public ArrayList<String> getTextLists() {
        return textLists;
    }

    public void setTextLists(ArrayList<String> textLists) {
        this.textLists = textLists;
    }

    private ArrayList<String> textLists = new ArrayList<String>();
    public void postMsg() {
        //eventBus.publish()
    }
}
