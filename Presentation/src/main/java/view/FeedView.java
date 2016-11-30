package view;

import services.FeedService;
import services.NavigationService;
import viewmodel.PostVM;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by cj on 2016-11-27.
 */
@ManagedBean
@ViewScoped
public class FeedView {

    private List<PostVM> newsFeed;
    private List<PostVM> profileFeed;

    @PostConstruct
    public void init() {
        System.out.println("FeedView::init");
        if (feedService == null) {
            System.out.println("feedService == null");
            return;
        }


    }

    @ManagedProperty("#{navigationService}")
    private NavigationService navigationService;



    @ManagedProperty("#{feedService}")
    private FeedService feedService;


    public List<PostVM> getNewsFeed() {
        System.out.println("geting newsFeed");
        newsFeed = feedService.getNewsFeed();
        return newsFeed;
    }

    public void setNewsFeed(List<PostVM> newsFeed) {
        this.newsFeed = newsFeed;
    }

    public List<PostVM> getProfileFeed() {
        profileFeed = feedService.getProfileFeed(navigationService.getSelectedUserId());
        return profileFeed;
    }

    public void setProfileFeed(List<PostVM> profileFeed) {
        this.profileFeed = profileFeed;
    }

    public FeedService getFeedService() {
        return feedService;
    }

    public void setFeedService(FeedService feedService) {
        this.feedService = feedService;
    }

    public NavigationService getNavigationService() {
        return navigationService;
    }

    public void setNavigationService(NavigationService navigationService) {
        this.navigationService = navigationService;
    }
}
