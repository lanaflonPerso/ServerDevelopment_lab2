package com.serverutvlab.business.BModels;

import com.serverutvlab.services.SModels.SPost;

import java.util.List;

/**
 * Created by cj on 2016-11-18.
 */
public class BProfile {
    private int id;
    private String name;
    private String info;
    private Integer age;
    private int relationshipStatus;
    private int userId;
    private List<BPost> wallPosts;


    public BProfile() {
    }

    public BProfile(int id, String name, String info, Integer age, int relationshipStatus, int userId, List<BPost> wallPosts) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.age = age;
        this.relationshipStatus = relationshipStatus;
        this.userId = userId;
        this.wallPosts = wallPosts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(int relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<BPost> getWallPosts() {
        return wallPosts;
    }

    public void setWallPosts(List<BPost> wallPosts) {
        this.wallPosts = wallPosts;
    }

    @Override
    public String toString() {
        return "BProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", age=" + age +
                ", relationshipStatus=" + relationshipStatus +
                ", userId=" + userId +
                ", wallPosts=" + wallPosts +
                '}';
    }
}
