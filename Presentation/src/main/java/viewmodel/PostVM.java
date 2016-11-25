package viewmodel;

import backend.SModels.SProfile;

import java.sql.Timestamp;

/**
 * Created by cj on 2016-11-25.
 */
public class PostVM {

    private int id;
    private String subject;
    private String messageBody;
    private String authorName;
    private String recipientName;
    private Timestamp timestamp;
    private int authorId;
    private int recipientId;
    private boolean isPrivate;


    public PostVM(int id, String subject, String messageBody, String authorName, String recipientName, Timestamp timestamp, int authorId, int recipientId, boolean isPrivate){
        this.id = id;
        this.subject = subject;
        this.messageBody = messageBody;
        this.authorName = authorName;
        this.recipientName = recipientName;
        this.timestamp = timestamp;
        this.authorId = authorId;
        this.recipientId = recipientId;
        this.isPrivate = isPrivate;
    }

    public PostVM(int id, String subject, String messageBody, Timestamp timestamp, int authorId, int recipientId){
        this.id = id;
        this.subject = subject;
        this.messageBody = messageBody;
        this.timestamp = timestamp;
        this.authorId = authorId;
        this.recipientId = recipientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    @Override
    public String toString() {
        return "SPost{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", authorName='" + authorName + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", timestamp=" + timestamp +
                ", authorId=" + authorId +
                ", recipientId=" + recipientId +
                ", isPrivatePost=" + isPrivate +
                '}';
    }
}

