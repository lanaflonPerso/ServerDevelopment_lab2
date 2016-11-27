package chat;

import frontendcoder.MessageCoder;
import org.primefaces.push.EventBus;
import org.primefaces.push.RemoteEndpoint;
import org.primefaces.push.annotation.*;

/**
 * Created by o_0 on 2016-11-21.
 */
@PushEndpoint("/chat/{room}/{user}")
@Singleton
public class ChatData {
    private static final String CHANNEL = "/chat/";
    @PathParam("room")
    private  String theRoom;

    @PathParam("user")
    private String theUser;

    @OnOpen
    public void onOpen(RemoteEndpoint r, EventBus eventBus)
    {
        System.out.println("ChatData::onOpen theRoom = " + theRoom + " the user = " + theUser);
        eventBus.publish(CHANNEL + theRoom + "/" + theUser,new ChatMessageVM("onOpen",theUser,theRoom));
    }
    @OnClose
    public void onClose(RemoteEndpoint r, EventBus eventBus)
    {
        System.out.println("ChatData::onClose theRoom = " + theRoom + " the user = " + theUser);
        eventBus.publish(CHANNEL + theRoom + "/" + theUser,new ChatMessageVM("onClose",theUser,theRoom));
    }

    @OnMessage(decoders = {MessageCoder.class}, encoders = {MessageCoder.class})
    public ChatMessageVM onMessage(ChatMessageVM message) {
        System.out.println("ChatData::onMessage" + theRoom + " : " + theUser);
        return message;
    }
}
