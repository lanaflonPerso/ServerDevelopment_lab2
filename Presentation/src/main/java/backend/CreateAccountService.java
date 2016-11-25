package backend;

import account.Account;
import viewmodel.UserVM;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 * Created by o_0 on 2016-11-25.
 */
@ManagedBean(name = "createAccountService")
@SessionScoped
public class CreateAccountService {

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

    public String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    @PostConstruct
    public void init() {
        this.errorMessage = "";
    }

    public boolean createAccount(String username, String password) {
        // TODO: hookup to backend
        UserVM user = BackendFacade.registerUser(username,password);// new UserVM(3,"d1");   /// BackendFacade.registrateUser(userName,password)
        if (user != null) {
            userAccount.fillAccount(user);
            userAccount.setLoggedin(true);
            profileService.selectProfile(user.getUserId());
            return true;
        }else {
            this.errorMessage = "Change user name";
        }
        return false;
    }

}
