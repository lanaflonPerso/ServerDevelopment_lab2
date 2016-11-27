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

    /**
     * servericename
     * @return service name
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getServiceName()
    {
       return "Name: UserService";
    }

    /**
     * returning users
     * @return
     */
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

    /**
     * register a user
     * @param email
     * @param password
     * @return
     */
    @POST
    @Path("registerUser")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registerUser(@QueryParam("email") String email, @QueryParam("password") String password) {

        //System.out.println("Login attempt:Email = " + email);
        //System.out.println("Login attempt:Password = " + password);

        SUser result = BFacade.registerUser(email,password);

        Gson gson = new Gson();
        String response = gson.toJson(result);

        return Response.ok(response).build();
    }

    /**
     * authenticates user
     * @param email
     * @param password
     * @return
     */
    @POST
    @Path("login")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response authenticateUser(@QueryParam("email") String email, @QueryParam("password") String password) {

        System.out.println("Login attempt:Email = " + email);
        System.out.println("Login attempt:Password = " + password);

        SUser sUser = BFacade.authenticateUser(email, password);

        if (sUser == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Gson gson = new Gson();
        String response = gson.toJson(sUser);
        return Response.ok(response).build();
    }

    /**
     * get user by id
     * @param id
     * @return
     */
    @POST
    @Path("userById")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUserById(@QueryParam("id") int id) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();

        SUser result = BFacade.getUserById(id);

        Gson gson = new Gson();
        String response = gson.toJson(result);
        System.out.println("getUserById: " + id + " suser: "  + result );
        return Response.ok(response).build();
    }

    /**
     * get friends by user id
     * @param id
     * @return
     */
    @GET
    @Path("getFriendsByUserId")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response getFriendsByUserId(@QueryParam("userId") int id) {

        List<SUser> result = BFacade.getFriendsByUserId(id);

        Gson gson = new Gson();
        String response = gson.toJson(result);

        return Response.ok(response).build();
    }

    /**
     * adding a friendship between id's
     * @param uId
     * @param fId
     * @return
     */
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
    /**
     * removing a friendship between id's
     * @param uId
     * @param fId
     * @return
     */
    @POST
    @Path("removeFriend")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response removeFriendToUser(@QueryParam("userId") int uId, @QueryParam("friendId")int fId) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
        boolean result = BFacade.removeFriendToUser(uId,fId);

        resultMap.put("success", result);

        Gson gson = new Gson();
        String response = gson.toJson(resultMap);

        return Response.ok(response).build();
    }

    /**
     * get non friends
     * @param userId
     * @return might return all users if you are forever lonely
     */
    @GET
    @Path("getNonFriends")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getNonFriends(@QueryParam("userId") int userId){

        List<SUser> result = BFacade.getNonFriendsByUserId(userId);

        Gson gson = new Gson();
        String response = gson.toJson(result);

        return Response.ok(response).build();
    }
}
