package backend;

import account.Account;
import viewmodel.ProfileItem;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 * Created by o_0 on 2016-11-24.
 */
@ManagedBean(name = "profileService")
@SessionScoped
public class ProfileService implements ProfileItem{

    @ManagedProperty("#{account}")
    private Account userAccount;

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }


    private String name;
    private String info;
    private Integer age;
    private int relationshipStatus;

    private int profileUserId;

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

    public boolean saveUserProfile() {
        System.out.println("ProfileView::saveUserProfile");
        boolean success = false;
        //this.responseMessage = "failed";
        if (userAccount.isLoggedin()) {
            // TODO: parse id to correct one
            success = BackendFacade.saveProfile(userAccount.getUserId(), this);
//            this.responseMessage = success ? "profile saved" : "failed to save";

        }else {
//            this.responseMessage = "Not loggedin";
            System.out.println("not loggedin");
        }
        return success;
    }

    public boolean loadUserProfile(int userId) {

        return false;
    }
}
