package view;

import chat.ChatMessageVM;
import frontendcoder.MessageCoder;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PathParam;
import org.primefaces.push.annotation.PushEndpoint;
import frontendcoder.NotificationCoder;
import viewmodel.NotificationVM;

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

    @OnMessage(decoders = {NotificationCoder.class}, encoders = {NotificationCoder.class})
    public NotificationVM onMessage(NotificationVM message) {
        System.out.println("NotifyChannel::onMessage: " + chuser);
        return message;
    }
}
