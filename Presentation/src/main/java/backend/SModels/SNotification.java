package backend.SModels;

/**
 * Created by o_0 on 2016-11-25.
 */
public class SNotification {
    private String message;

    public SNotification() {
    }

    public SNotification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
