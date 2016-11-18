package com.serverutvlab.services;

import com.google.gson.Gson;
import com.serverutvlab.business.BFacade;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by cj on 2016-11-18.
 */
@Path("profileservice")
public class ProfileService {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getServiceName() {
        return "Name: ProfileService";
    }


    @POST
    @Path("registerUser")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUserProfile(@QueryParam("email") int userId) {

        Gson gson = new Gson();
        String response = gson.toJson("respones goes here");

        return Response.ok(response).build();

    }
}
