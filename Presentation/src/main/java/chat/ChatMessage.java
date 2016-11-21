package chat;

/**
 * Created by o_0 on 2016-11-21.
 */
public class ChatMessage {
    private String message;
    private String fromUser;
    private String toUser;

    public ChatMessage(String message, String fromUser, String toUser) {
        this.message = message;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public ChatMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "message='" + message + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                '}';
    }
}
