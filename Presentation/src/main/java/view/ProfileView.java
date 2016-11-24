package view;

import account.Account;
import backend.BackendFacade;
import backend.ProfileService;
import viewmodel.ProfileVM;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by o_0 on 2016-11-23.
 */
@ManagedBean
@ViewScoped
public class ProfileView {

//    private int id;
    private String name;
    private String info;
    private Integer age;
    private int relationshipStatus;

    @ManagedProperty("#{profileService}")
    private ProfileService profileService;

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostConstruct
    public void init() {
        if (userAccount == null) {
            System.out.println("inint error (userAccount == null) ");
            return;
        }
        if (userAccount.isLoggedin() == false) {
            System.out.println("Not logged in" + userAccount);
        }
        //ProfileVM profileVM = BackendFacade.loadProfile(userAccount.getUserId());
        System.out.println("ProfileView namn: " + userAccount);
        this.responseMessage = "";
        //updateProfileInfo(profileVM);
    }

    @ManagedProperty("#{account}")
    private Account userAccount;

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
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

    private String showProfile;

    public String getShowProfile() {
        return showProfile;
    }

    public void setShowProfile(String showProfile) {
        this.showProfile = showProfile;
    }

    private void updateProfileInfo(ProfileVM profileVM) {
        this.name = profileVM.getName();
        this.info = profileVM.getInfo();
        this.age = profileVM.getAge();
        this.relationshipStatus = profileVM.getRelationshipStatus();
    }

    private ProfileVM getProfileInfo() {
        ProfileVM profileVM = new ProfileVM(name, info, age, relationshipStatus);
        return profileVM;
    }

    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void saveUserProfile() {
        System.out.println("ProfileView::saveUserProfile");
        this.responseMessage = "failed";
        if (userAccount.isLoggedin()) {
            // TODO: parse id to correct one
            boolean success = BackendFacade.saveProfile(userAccount.getUserId(), getProfileInfo());
            this.responseMessage = success ? "profile saved" : "failed to save";

        }else {
            this.responseMessage = "Not loggedin";
            System.out.println("not loggedin");
        }
    }

    public void loadUserProfile() {
        this.responseMessage = "";
        if (showProfile == null ) {
            System.out.println("loadUserProfile showProfile null");
            return;
        }
        if (userAccount.isLoggedin()) {
            // TODO: parse id to correct one
//            ProfileVM profileVM = BackendFacade.loadProfile(1);
//            updateProfileInfo(profileVM);
        }else {
            System.out.println("not loggedin");
        }
    }
}
