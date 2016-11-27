package viewmodel;

/**
 * Created by o_0 on 2016-11-24.
 */
public interface ProfileItem {

    public int getProfileId();

    public String getName();

    public void setName(String name);

    public String getInfo();

    public void setInfo(String info);

    public Integer getAge();

    public void setAge(Integer age);

    public int getRelationshipStatus();

    public void setRelationshipStatus(int relationshipStatus);
}
