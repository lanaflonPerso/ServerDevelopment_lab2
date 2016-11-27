package backend;

import backend.SModels.SChannelNotification;
import chat.ChatMessageVM;
import com.google.gson.Gson;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 * Created by o_0 on 2016-11-25.
 */
public class ChannelRelay {
    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();
    private static final String CHANNEL = "/notify/";

    public boolean channeöNotification(SChannelNotification chNotifaction) {
        if (chNotifaction == null) {
            return false;
        }
        return channelRelay(chNotifaction.getTargetChannel(),chNotifaction.getJsonObject());
    }

    public boolean channelRelay(String channel,String json) {

        if (eventBus == null) {
            System.out.println("ChatChannelRelay::channelRelay failed eventbus == null");
            return false;
        }else {
//            eventBus.publish(CHANNEL + "", "message text");
            eventBus.publish(CHANNEL + channel,json);
        }
        return true;
    }
}