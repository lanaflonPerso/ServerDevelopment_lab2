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

    }
    @OnClose
    public void onClose(RemoteEndpoint r, EventBus eventBus)
    {

    }

}
