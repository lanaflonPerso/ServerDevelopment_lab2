package viewmodel;

import backend.SModels.SPost;

import java.util.List;

/**
 * Created by o_0 on 2016-11-23.
 */
public class ProfileVM implements ProfileItem{
    private int id;
    private String name;
    private String info;
    private Integer age;
    private int relationshipStatus;

    public ProfileVM(int id, String name, String info, Integer age, int relationshipStatus) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.age = age;
        this.relationshipStatus = relationshipStatus;
    }

    public ProfileVM(String name, String info, Integer age, int relationshipStatus) {
        this.name = name;
        this.info = info;
        this.age = age;
        this.relationshipStatus = relationshipStatus;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProfileVM{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", age=" + age +
                ", relationshipStatus=" + relationshipStatus +
                '}';
    }
}
