package DBLayer.DBModels;

import java.sql.Timestamp;

/**
 * Created by cj on 2016-12-13.
 */
public class DBMessage implements Comparable<DBMessage> {

    private int id;
    private int fromId;
    private int toId;
    private int groupId;
    private String fromName;
    private String toName;
    private String text;
    private Timestamp timestamp;

    public DBMessage() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int compareTo(DBMessage o) {

        return o.timestamp.compareTo(this.timestamp);
        //this.timestamp.compareTo(o.getTimestamp());
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object obj) {
        DBMessage m = (DBMessage) obj;

        return this.id == m.getId();
    }



    @Override
    public String toString() {
        return "DBLayer.DBModels.DBMessage{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", fromName='" + fromName + '\'' +
                ", toName='" + toName + '\'' +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp + "}\n";
    }
}
