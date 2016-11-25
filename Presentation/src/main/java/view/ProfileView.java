package view;

import account.Account;
import backend.BackendFacade;
import backend.ProfileService;
import viewmodel.PostVM;
import viewmodel.ProfileItem;
import viewmodel.ProfileVM;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

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
    private List<PostVM> feed;


    @PostConstruct
    public void init() {
        System.out.println("ProfileView::init");
        this.name ="";
        this.info = "";
        this.age = 0;
        this.relationshipStatus = 0;
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
        updateProfileInfo();
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

    @ManagedProperty("#{profileService}")
    private ProfileService profileService;

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public String getName() {
        return profileService.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return profileService.getInfo();
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getAge() {
        return profileService.getAge();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getRelationshipStatus() {
        return profileService.getRelationshipStatus();
    }

    public void setRelationshipStatus(int relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    private String showProfile;

    public String getShowProfile() {
        return showProfile;
    }

    public void showMyProfile() {
        setShowProfile(""+userAccount.getUserId());
    }

    public void setShowProfile(String showProfile) {
        int userId = 0;
        try {
            userId = Integer.parseInt(showProfile);

        } catch (NumberFormatException ex) {
            System.out.println("showProfile wrong format" + showProfile);
            return;
        }
        this.showProfile = showProfile;

        profileService.selectProfile(userId);
    }

    private void updateProfileInfo() {
        this.name = profileService.getName();
        this.info = profileService.getInfo();
        this.age = profileService.getAge();
        this.relationshipStatus = profileService.getRelationshipStatus();

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


    public List<PostVM> getFeed() {
        return profileService.getCurrentFeed();
    }

    public void setFeed(List<PostVM> feed) {
        this.feed = feed;
    }

    public void saveUserProfile() {
        System.out.println("ProfileView::saveUserProfile");
        this.responseMessage = "failed";
        if (userAccount.isLoggedin()) {
            // TODO: parse id to correct one
            boolean success = profileService.saveUserProfile(getProfileInfo());
            //BackendFacade.saveProfile(userAccount.getUserId(), getProfileInfo());
            this.responseMessage = success ? "profile saved" : "failed to save";

        }else {
            this.responseMessage = "Not loggedin";
            System.out.println("not loggedin");
        }
    }

    public void loadUserProfile() {
        this.responseMessage = "";
//        if (showProfile == null ) {
//            System.out.println("loadUserProfile showProfile null");
//            return;
//        }
        System.out.println("loadUserProfile");
        if (userAccount.isLoggedin()) {
            // TODO: parse id to correct one
            profileService.loadUserProfile();
            this.responseMessage = "loaded";
        }else {
            System.out.println("not loggedin");
        }
    }
}
