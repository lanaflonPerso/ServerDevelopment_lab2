package com.serverutvlab.business;

import com.serverutvlab.business.BModels.BPost;
import com.serverutvlab.business.BModels.BProfile;
import com.serverutvlab.business.BModels.BUser;
import com.serverutvlab.database.DBLayer.DBFacade;
import com.serverutvlab.services.SModels.SPost;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cj on 2016-11-18.
 */
public class BPostLogic {

    public List<BPost> getPostsByProfile(int profileId, int activeUserId) {
        List<BPost> result = new ArrayList<BPost>();
        List<BPost> posts = DBFacade.getPostsForProfile(profileId);
        BProfile activeUserProfile = DBFacade.getProfileForUserId(activeUserId);

        if (activeUserProfile == null)
            return result;

        for (BPost p: posts){
            if (p.isPrivate()){
                if (activeUserProfile.getId() == p.getAuthorId() || activeUserProfile.getId() == p.getRecipientId()){
                    result.add(p);
                }
            } else {
                result.add(p);
            }
        }

        return result;
    }

    public BPost postPost(int autoridId, int recipientId, String subject, String messageBody, boolean isPrivate) {
        BPost p = DBFacade.postPost(autoridId,recipientId,subject,messageBody,isPrivate);
        return p;
    }

    public List<BPost> getFeedForUser(int userId) {
        List<BPost> feed = new ArrayList<BPost>();
        feed.addAll(DBFacade.getProfileForUserId(userId).getWallPosts());
        System.out.println("Feed count after adding own posts: " + feed.size());

        List<BUser> friends = DBFacade.getFriendsByUserId(userId);

        for (BUser f: friends){
            BProfile p = DBFacade.getProfileForUserId(f.getId());
            for (BPost post:DBFacade.getPostsForProfile(p.getId())) {
                if (!feed.contains(post) && !post.isPrivate())
                    feed.add(post);
            }
        }

        System.out.println("Feed count after adding friends posts: " + feed.size());

        return feed;
    }
}
