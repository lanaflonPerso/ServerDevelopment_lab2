package viewmodel;

/**
 * Created by o_0 on 2016-11-27.
 */
public class NotificationVM {
    private String message;
    private String what;

    public NotificationVM() {
    }

    public NotificationVM(String message, String what) {
        this.message = message;
        this.what = what;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    @Override
    public String toString() {
        return "NotificationVM{" +
                "message='" + message + '\'' +
                ", what='" + what + '\'' +
                '}';
    }
}
