package view;

import account.Account;
import backend.BackendFacade;
import services.ProfileService;
import services.NavigationService;
import viewmodel.PostVM;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by o_0 on 2016-11-25.
 */
@ManagedBean
@ViewScoped
public class PostView {
    @ManagedProperty("#{account}")
    private Account userAccount;

    @ManagedProperty("#{profileService}")
    private ProfileService profileService;

    @ManagedProperty("#{navigationService}")
    private NavigationService navigationService;

    private String subject;
    private String messageBody;
    private int recipientId;
    private boolean privatePost;

    @PostConstruct
    public void init() {
        this.privatePost = false;

    }
    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }


    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public NavigationService getNavigationService() {
        return navigationService;
    }

    public void setNavigationService(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    public String getSubject() {
        return subject;
    }

    public String getRecipientName() {
        return profileService.getName();
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

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public boolean isPrivatePost() {
        return privatePost;
    }

    public void setPrivatePost(boolean privatePost) {
        this.privatePost = privatePost;
    }

    public void sendPost() {
        if (userAccount.isLoggedin()) {
            PostVM postVM = BackendFacade.postPost(userAccount.getUserId(), navigationService.getSelectedUserId(), subject, messageBody, privatePost);
            //profileService.posted(postVM);
        }
    }
}
