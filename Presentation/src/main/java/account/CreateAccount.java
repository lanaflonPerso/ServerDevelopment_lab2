package account;

import services.CreateAccountService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by o_0 on 2016-11-25.
 */
@ManagedBean
@ViewScoped
public class CreateAccount {
    @ManagedProperty("#{account}")
    private Account userAccount;

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    @ManagedProperty("#{createAccountService}")
    private CreateAccountService createAccountService;

    public CreateAccountService getCreateAccountService() {
        return createAccountService;
    }

    public void setCreateAccountService(CreateAccountService createAccountService) {
        this.createAccountService = createAccountService;
    }

    private String userName = null;
    private String password = null;
    @PostConstruct
    public void init() {
        this.userName = null;
        this.password = null;
        this.createStatus = "";
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String nextPage;


    private String createStatus;

    public String getCreateStatus() {
        return createStatus;
    }

    public void setCreateStatus(String createStatus) {
        this.createStatus = createStatus;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void createUser() {
        if (userName == null && password == null) {
            createStatus = "Please enter username and password";
        }
        if (createAccountService.createAccount(userName,password)) {
            createStatus = "success";
        }else {
            createStatus = "failed: " + createAccountService.errorMessage;
        }

    }
}
