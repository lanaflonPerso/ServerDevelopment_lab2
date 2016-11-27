package viewmodel;

/**
 * Created by o_0 on 2016-11-27.
 */
public class NotificationVM {
    private String data;
    private String what;

    public NotificationVM() {
    }

    public NotificationVM(String data, String what) {
        this.data = data;
        this.what = what;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
                "data='" + data + '\'' +
                ", what='" + what + '\'' +
                '}';
    }
}
