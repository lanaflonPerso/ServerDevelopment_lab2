package navigationcontroller;

import account.Account;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 * Created by o_0 on 2016-11-03.
 */
@ManagedBean
@SessionScoped
public class NavigationController {
    public String moveToStart() {
        return "newHome";
    }

    public String moveToPage() {
        return "newHome";
    }

    @ManagedProperty("#{account}")
    private Account userAccount;

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }


    public String moveToSelection(String id) {
        if (userAccount.isLoggedin()) {
            if(id.equals("1")) {
                return "newHome";
            }else if(id.equals("2")){
                return "GetNewFriends";
            }else if(id.equals("4")){
                return "chatwindow";
            }else if(id.equals("6")){
                return "profileEdit";
            }
        }
        if(id.equals("5")){
            return "login";
        } else if(id.equals("0")){
            return "index";
        }
        return "index";
    }


}
