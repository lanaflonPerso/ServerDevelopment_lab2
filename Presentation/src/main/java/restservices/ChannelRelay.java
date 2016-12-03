package restservices;

import restservices.SModels.SChannelNotification;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import viewmodel.NotificationVM;

/**
 * Created by o_0 on 2016-11-25.
 */
public class ChannelRelay {
    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();
    private static final String CHANNEL = "/notify/";

    public boolean channelNotification(SChannelNotification chNotifaction) {
        if (chNotifaction == null) {
            return false;
        }
        String what = chNotifaction.getWhat();
        if (what.equals("newfriend") ){
            return channelRelay(chNotifaction.getTargetChannel(),new NotificationVM(chNotifaction.getJsonObject(),what));

        } else if (what.equals("chatRequest") ){
            System.out.println("channelNotification:chatRequest -> json: " + chNotifaction.getJsonObject() );
            return channelRelay(chNotifaction.getTargetChannel(),new NotificationVM(chNotifaction.getJsonObject(),what));

        }else {
            System.out.println("channelNotification  failed what = " + what);
        }

        return channelRelayJson(chNotifaction.getTargetChannel(),chNotifaction.getJsonObject());
    }

    public boolean channelRelay(String channel, NotificationVM notication) {

        if (eventBus == null) {
            System.out.println("ChatChannelRelay::channelRelay failed eventbus == null");
            return false;
        }else {
//            eventBus.publish(CHANNEL + "", "message text");
            System.out.println("channelRelay: eventbus: channel: " + CHANNEL + channel + " notif: " + notication.toString());
            eventBus.publish(CHANNEL + channel,notication);
        }
        return true;
    }

    public boolean channelRelayJson(String channel,String json) {

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