package chat;

import org.primefaces.push.EventBus;
import org.primefaces.push.RemoteEndpoint;
import org.primefaces.push.annotation.*;

/**
 * Created by o_0 on 2016-11-21.
 */
@PushEndpoint("/{room}/{user}")
@Singleton
public class ChatData {

    @PathParam("room")
    private  String theRoom;

    @PathParam("user")
    private String theUser;

    @OnOpen
    public void onOpen(RemoteEndpoint r, EventBus eventBus)
    {
        System.out.println("ChatData::onOpen");
        eventBus.publish(theRoom + "/*",new ChatMessage("onOpen","user 1","user2"));
    }
    @OnClose
    public void onClose(RemoteEndpoint r, EventBus eventBus)
    {
        System.out.println("ChatData::onClose");
        eventBus.publish(theRoom + "/*",new ChatMessage("onClose","user 1","user2"));
    }

    @OnMessage(decoders = {MessageCoder.class}, encoders = {MessageCoder.class})
    public ChatMessage onMessage(ChatMessage message) {
        System.out.println("ChatData::onMessage");
        return message;
    }
}
