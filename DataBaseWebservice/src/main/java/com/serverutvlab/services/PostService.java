package com.serverutvlab.services;

import com.google.gson.Gson;
import com.serverutvlab.business.BFacade;
import com.serverutvlab.services.SModels.SPost;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by cj on 2016-11-21.
 */

@Path("postservice")
public class PostService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getServiceName() {
        return "Name: PostService";
    }


    // TODO: 2016-11-21 authenticated call
    @GET
    @Path("getprofileposts")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getPostsByProfileId(@QueryParam("profileId") int profileId,
                                        @QueryParam("activeUserId") int activeUserId){

        List<SPost> posts = BFacade.getPostsByProfile(profileId, activeUserId);

        Gson gson = new Gson();
        String response = gson.toJson(posts);

        return Response.ok(response).build();
    }

    // TODO: 2016-11-21 authenticated call
    @POST
    @Path("postposttoprofile")
    @Produces(MediaType.TEXT_PLAIN)
    public Response postPost(@QueryParam("authorId") int autoridId,
                             @QueryParam("recipientId") int recipientId,
                             @QueryParam("subject") String subject,
                             @QueryParam("messageBody") String messageBody,
                             @QueryParam("isPrivate") boolean isPrivate){

        SPost post = BFacade.postPost(autoridId,recipientId,subject,messageBody,isPrivate);

        Gson gson = new Gson();
        String response = gson.toJson(post);

        return Response.ok(response).build();
    }

    @GET
    @Path("getFeed")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getFeedById(@QueryParam("userId") int userId){
        List<SPost> feed = BFacade.getFeedByUser(userId);

        Gson gson = new Gson();
        String response = gson.toJson(feed);

        return Response.ok(response).build();
    }


}
