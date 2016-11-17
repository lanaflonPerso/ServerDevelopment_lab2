package notificationexperiment;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.UUID;

/**
 * Created by cj on 2016-11-03.
 */
@ManagedBean
@SessionScoped
public class GrowlBean {

    private String channel;

    @ManagedProperty(value ="#{channelsBean}")
    private ChannelsBean channels;

    private String sendMessageUser;

    private String user;

    @PostConstruct
    public void doPostConstruction(){
        channel = "/" + UUID.randomUUID().toString();
        channels.addChannel(user, channel);
    }

    public void send(){
        System.out.println("Before getting event bus");
        EventBus busPushContext = EventBusFactory.getDefault().eventBus();
        System.out.println("After getting event bus");

        busPushContext.publish(channels.getChannel(sendMessageUser), new FacesMessage("Hi from god", user));

    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public ChannelsBean getChannels() {
        return channels;
    }

    public void setChannels(ChannelsBean channels) {
        this.channels = channels;
    }

    public String getSendMessageUser() {
        return sendMessageUser;
    }

    public void setSendMessageUser(String sendMessageUser) {
        this.sendMessageUser = sendMessageUser;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
