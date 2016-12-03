package restservices.SModels;

/**
 * Created by o_0 on 2016-11-25.
 */
public class SChannelNotification {
    private String targetChannel;
    private String what;
    private String jsonObject;

    public SChannelNotification() {
    }

    public SChannelNotification(String targetChannel, String what, String jsonObject) {
        this.targetChannel = targetChannel;
        this.what = what;
        this.jsonObject = jsonObject;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getTargetChannel() {
        return targetChannel;
    }

    public void setTargetChannel(String targetChannel) {
        this.targetChannel = targetChannel;
    }

    public String getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(String jsonObject) {
        this.jsonObject = jsonObject;
    }
}
