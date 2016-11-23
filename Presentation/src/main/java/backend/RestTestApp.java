package backend;

import chat.ChatMessageVM;
import com.google.gson.Gson;

/**
 * Created by o_0 on 2016-11-22.
 */
public class RestTestApp {
    public static void main(String[] args) {
        System.out.println("rest test");
        ChatMessageVM chatMessageVM = new ChatMessageVM("the message", "this is me", "recive it dude");
        String s = new Gson().toJson(chatMessageVM, ChatMessageVM.class);
        System.out.println("Sending json string: " + s);

        String s1 = RestBackendLink.doRestJsonPost("services/chatService/","sendmessage",s);
        System.out.println("Response is: " + s1);


    }
}
