package chat;

import com.google.gson.Gson;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 * Created by o_0 on 2016-11-22.
 */
public class ChatChannelRelay {
    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();
    //private static final String CHANNEL = "/{room}/";
    private static final String CHANNEL = "/chat/";

    public boolean channelRelay(String messageJson) {
        ChatMessageVM chatMessageVM = new Gson().fromJson(messageJson, ChatMessageVM.class);
        if (eventBus == null) {
            System.out.println("ChatChannelRelay::channelRelay failed eventbus == null");
            return false;
        }else {
            System.out.println("ChatChannelRelay:channelRelay: channel: " + CHANNEL + chatMessageVM.getToUser() + "/" + chatMessageVM.getFromUser());
//            eventBus.publish(CHANNEL + "", "message text");
            eventBus.publish(CHANNEL + chatMessageVM.getToUser() + "/" + chatMessageVM.getFromUser(), chatMessageVM);
//            eventBus.publish(CHANNEL + chatMessageVM.getFromUser(), chatMessageVM);
        }
        return true;
    }
}
