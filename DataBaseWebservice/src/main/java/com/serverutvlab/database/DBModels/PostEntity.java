package com.serverutvlab.database.DBModels;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by cj on 2016-11-18.
 */
@Entity
public class PostEntity {
    private int id;
    private String subject;
    private String messageBody;
    private Timestamp timestamp;
    private String authorName;
    private String recipientName;
    private int authorId;
    private int recipientId;
    private ProfileEntity postedTo;
    private boolean privatePost;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "messageBody")
    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    @Basic
    @Column(name = "timestamp")
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Basic
    @Column(name = "authorId")
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Basic
    @Column(name = "recipientId")
    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostEntity that = (PostEntity) o;

        if (id != that.id) return false;
        if (authorId != that.authorId) return false;
        if (recipientId != that.recipientId) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (messageBody != null ? !messageBody.equals(that.messageBody) : that.messageBody != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;

        return true;
    }

    @Basic
    @Column(name = "authorName")
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    @Basic
    @Column(name = "recipientName")
    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
    @Basic
    @Column(name = "privatePost")
    public boolean isPrivatePost() {
        return privatePost;
    }

    public void setPrivatePost(boolean privatePost) {
        this.privatePost = privatePost;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (messageBody != null ? messageBody.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + authorId;
        result = 31 * result + recipientId;
        return result;
    }

    @ManyToOne(optional = false)
    public ProfileEntity getPostedTo() {
        return postedTo;
    }

    public void setPostedTo(ProfileEntity postedTo) {
        this.postedTo = postedTo;
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", timestamp=" + timestamp +
                ", authorName='" + authorName + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", authorId=" + authorId +
                ", recipientId=" + recipientId +
                ", postedTo=" + postedTo +
                ", privatePost=" + privatePost +
                '}';
    }
}
