package view;

import chat.ChatMessageVM;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by o_0 on 2016-11-25.
 */
@ManagedBean
@RequestScoped
public class NotificationView {

    private final String CHANNEL = "/notify/";

    private String msg;
    private String ch;

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void notifySend() {
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        if (eventBus == null) {
            System.out.println("notifySend:eventbus null");
            return;
        }
        eventBus.publish(CHANNEL + ch, new ChatMessageVM(msg,ch,"touser"));
    }
}
