package viewmodel;

import com.google.gson.Gson;
import org.primefaces.push.Decoder;
import org.primefaces.push.Encoder;


public class NotificationCoder implements Decoder<String, NotificationVM>, Encoder<NotificationVM,String> {
    /**
     * Decode the specified object of type U into object of type T
     *
     * @param s a object of type U
     * @return a new object of type T
     */
    public NotificationVM decode(String s) {
        System.out.println("MessageCoder::decode json: " + s );
        NotificationVM notificationVM = new Gson().fromJson(s, NotificationVM.class);
        System.out.println("MessageCoder::decode result: " + notificationVM.toString());
        return notificationVM;
    }

    /**
     * Encode the object of type U into an object of type T.
     *
     * @param s an object that has already been encoded or returned from an @Message annotated class.
     * @return an encoded object.
     */
    public String encode(NotificationVM s) {
        System.out.println("MessageCoder::encode chatmessage: " + s.toString() );
        String json = new Gson().toJson(s);
        System.out.println("MessageCoder::encode json: " + json);
        return json;
    }
}
