package com.serverutvlab.services;

import com.google.gson.Gson;
import com.serverutvlab.business.BFacade;
import com.serverutvlab.services.SModels.SUser;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by o_0 on 2016-11-14.
 */
@Path("userservice")
public class UserService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getServiceName()
    {
       return "Name: UserService";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("users")
    public String getUsers()
    {
        List<SUser> allUsers = BFacade.getAllUsers();
        Gson gson = new Gson();
        String json = gson.toJson(allUsers);
        return json;
    }

    @POST
    @Path("registerUser")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registerUser(@QueryParam("email") String email, @QueryParam("password") String password) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();

        //System.out.println("Login attempt:Email = " + email);
        //System.out.println("Login attempt:Password = " + password);

        boolean result = BFacade.registerUser(email,password);

        resultMap.put("success", result);

        Gson gson = new Gson();
        String response = gson.toJson(resultMap);

        return Response.ok(response).build();
    }


    @POST
    @Path("login")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response authenticateUser(@QueryParam("email") String email, @QueryParam("password") String password) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();

        //System.out.println("Login attempt:Email = " + email);
        //System.out.println("Login attempt:Password = " + password);

        boolean result = BFacade.authenticateUser(email,password);

        resultMap.put("success", result);

        Gson gson = new Gson();
        String response = gson.toJson(resultMap);

        return Response.ok(response).build();
    }

    @POST
    @Path("userById")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUserById(@QueryParam("id") int id) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();

        SUser result = BFacade.getUserById(id);

        Gson gson = new Gson();
        String response = gson.toJson(result);

        return Response.ok(response).build();
    }

    @POST
    @Path("getFriendsByUserId")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response getFriendsByUserId(@QueryParam("userId") int id) {

        List<SUser> result = BFacade.getFriendsByUserId(id);

        Gson gson = new Gson();
        String response = gson.toJson(result);

        return Response.ok(response).build();
    }

    @POST
    @Path("addFriendToUser")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addFriendToUser(@QueryParam("userId") int uId, @QueryParam("friendId")int fId) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();

        boolean result = BFacade.addFriendToUser(uId,fId);

        resultMap.put("success", result);

        Gson gson = new Gson();
        String response = gson.toJson(resultMap);

        return Response.ok(response).build();
    }

}
