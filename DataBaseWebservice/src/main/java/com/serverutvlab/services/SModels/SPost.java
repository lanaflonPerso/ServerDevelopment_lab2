package com.serverutvlab.services.SModels;

import com.serverutvlab.database.DBModels.ProfileEntity;

import java.sql.Timestamp;

/**
 * Created by cj on 2016-11-18.
 */
public class SPost {
    private int id;
    private String subject;
    private String messageBody;
    private Timestamp timestamp;
    private int authorId;
    private int recipientId;
    private SProfile postedTo;

    public SPost(int id, String subject, String messageBody, Timestamp timestamp, int authorId, int recipientId) {
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

    public SProfile getPostedTo() {
        return postedTo;
    }

    public void setPostedTo(SProfile postedTo) {
        this.postedTo = postedTo;
    }

    @Override
    public String toString() {
        return "SPost{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", timestamp=" + timestamp +
                ", authorId=" + authorId +
                ", postedTo=" + postedTo +
                '}';
    }
}
