package chat;

import com.google.gson.Gson;
import org.primefaces.push.Decoder;
import org.primefaces.push.Encoder;

/**
 * Created by o_0 on 2016-11-21.
 */
public class MessageCoder implements Decoder<String, ChatMessageVM>, Encoder<ChatMessageVM,String> {
    /**
     * Decode the specified object of type U into object of type T
     *
     * @param s a object of type U
     * @return a new object of type T
     */
    public ChatMessageVM decode(String s) {
        System.out.println("MessageCoder::decode json: " + s );
        ChatMessageVM chatMessageVM = new Gson().fromJson(s, ChatMessageVM.class);
        System.out.println("MessageCoder::decode result: " + chatMessageVM.toString());
        return chatMessageVM;
    }

    /**
     * Encode the object of type U into an object of type T.
     *
     * @param s an object that has already been encoded or returned from an @Message annotated class.
     * @return an encoded object.
     */
    public String encode(ChatMessageVM s) {
        System.out.println("MessageCoder::encode chatmessage: " + s.toString() );
        String json = new Gson().toJson(s);
        System.out.println("MessageCoder::encode json: " + json);
        return json;
    }
}
