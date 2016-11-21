package com.serverutvlab.business;

import com.serverutvlab.business.BModels.BPost;
import com.serverutvlab.database.DBLayer.DBFacade;
import com.serverutvlab.services.SModels.SPost;

import java.util.List;

/**
 * Created by cj on 2016-11-18.
 */
public class BPostLogic {
    public List<BPost> getPostsByProfile(int profileId) {
        List<BPost> posts = DBFacade.getPostsForProfile(profileId);
        return posts;
    }

    public BPost postPost(int autoridId, int recipientId, String subject, String messageBody) {
        BPost p = DBFacade.postPost(autoridId,recipientId,subject,messageBody);
        return p;
    }
}
