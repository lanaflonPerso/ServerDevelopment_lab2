package backend.SModels;

/**
 * Created by o_0 on 2016-11-25.
 */
public class SChannelNotification {
    private String targetChannel;
    private String jsonObject;

    public SChannelNotification() {
    }

    public SChannelNotification(String targetChannel, String jsonObject) {
        this.targetChannel = targetChannel;
        this.jsonObject = jsonObject;
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
