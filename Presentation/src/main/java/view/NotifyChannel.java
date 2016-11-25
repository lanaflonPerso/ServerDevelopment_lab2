package view;

import chat.ChatMessageVM;
import chat.MessageCoder;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PathParam;
import org.primefaces.push.annotation.PushEndpoint;

/**
 * Created by o_0 on 2016-11-25.
 */
@PushEndpoint("/notify/{chuser}")
public class NotifyChannel {
    @PathParam("chuser")
    private  String chuser;

    @OnMessage(decoders = {MessageCoder.class}, encoders = {MessageCoder.class})
    public ChatMessageVM onMessage(ChatMessageVM message) {
        System.out.println("NotifyChannel::onMessage: " + chuser);
        return message;
    }
}
