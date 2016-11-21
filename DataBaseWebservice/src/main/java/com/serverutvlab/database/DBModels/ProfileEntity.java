package com.serverutvlab.database.DBModels;

import javax.persistence.*;
import java.util.List;

/**
 * Created by cj on 2016-11-18.
 */
@Entity
public class ProfileEntity {
    private int id;
    private String name;
    private String info;
    private Integer age;
    private int relationshipStatus;
    private int userId;
    private List<PostEntity> WallPost;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "relationshipStatus")
    public int getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(int relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    @Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileEntity that = (ProfileEntity) o;

        if (id != that.id) return false;
        if (relationshipStatus != that.relationshipStatus) return false;
        if (userId != that.userId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + relationshipStatus;
        result = 31 * result + userId;
        return result;
    }

    @OneToMany(mappedBy = "postedTo")
    public List<PostEntity> getWallPost() {
        return WallPost;
    }

    public void setWallPost(List<PostEntity> wallPost) {
        WallPost = wallPost;
    }


}
