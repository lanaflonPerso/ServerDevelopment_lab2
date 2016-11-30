package navigationcontroller;

import account.Account;
import services.NavigationService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 * Created by o_0 on 2016-11-03.
 */
@ManagedBean
@SessionScoped
public class NavigationController {

    private boolean renderContent;
    private boolean renderFriend;
    private boolean renderChatReq;

    private static final String indexPage = "index";
    private String currentPage = "index";

    @ManagedProperty("#{account}")
    private Account userAccount;

    @ManagedProperty("#{navigationService}")
    private NavigationService navigationService;



    @PostConstruct
    public void init() {
        this.renderContent = true;
        this.renderFriend = true;
        this.renderChatReq = false;
        this.currentPage = "feed";
        navigationService.setSelectedUserId(userAccount.getUserId());
    }


    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    public NavigationService getNavigationService() {
        return navigationService;
    }

    public void setNavigationService(NavigationService navigationService) {
        this.navigationService = navigationService;
    }


    public int getSelectedUserId() {
        return navigationService.getSelectedUserId();
    }

    public void setSelectedUserId(int selectedUserId) {
        navigationService.setSelectedUserId(selectedUserId);
    }

    public boolean isRenderContent() {
        boolean tmp = renderContent;
        //renderContent = false;
        return tmp;
    }

    public void setRenderContent(boolean renderContent) {
        this.renderContent = renderContent;
    }

    public boolean isRenderFriend() {
        boolean tmp = renderFriend;
        //renderFriend = false;
        return tmp;
    }
    public boolean isRenderChatReq() {
        return renderChatReq;
    }

    public void setRenderChatReq(boolean renderChatReq) {
        this.renderChatReq = renderChatReq;
    }

    public void setRenderFriend(boolean renderFriend) {
        this.renderFriend = renderFriend;
    }



    public String moveToStart() {
        return "newHome";
    }

    public String moveToPage() {
        return "newHome";
    }



    public String getPage() {
        return currentPage;
    }

    public void setPage(String page) {
        this.currentPage = page;
        //this.renderContent = true;
    }

    public void setChatBoxPage() {
        this.renderChatReq = false;
        this.currentPage = "chatbox";
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
            }else if(id.equals("42")){
                return "newHome";
            }
        }
        if(id.equals("5")){
            return "login";
        }else if(id.equals("8")){
            return "createuser";
        }else if(id.equals("9")){
            return "notifytest";
        } else if(id.equals("0")){
            return "index";
        }

        return "index";
    }

    public String getNotificationChannel() {
        return "ch" + userAccount.getUserId();
    }

    public String goToStart() {
        return indexPage;

    }


}
