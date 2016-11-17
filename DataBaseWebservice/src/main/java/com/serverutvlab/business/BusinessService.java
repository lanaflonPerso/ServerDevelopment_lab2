package com.serverutvlab.business;

        import com.google.gson.Gson;
        import com.serverutvlab.business.BModels.BUser;
        import com.serverutvlab.database.DBLayer.DBFacade;
        import com.serverutvlab.database.DBLayer.UserLogic;
        import com.serverutvlab.database.DBModels.UserEntity;

        import javax.print.attribute.standard.Media;
        import javax.ws.rs.*;
        import javax.ws.rs.core.Application;
        import javax.ws.rs.core.MediaType;
        import javax.ws.rs.core.Response;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

/**
 * Created by o_0 on 2016-11-14.
 */
@Path("businessservice")
public class BusinessService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getBusinessName()
    {
       return "Name: BusinessService";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("users")
    public String getBusinessUsers()
    {
        List<BUser> allUsers = DBFacade.getAllUsers();
        Gson gson = new Gson();
        String json = gson.toJson(allUsers);
        return json;
    }

    @POST
    @Path("login")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response authenticateUser(@QueryParam("email") String email, @QueryParam("password") String password) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();

        //System.out.println("Login attempt:Email = " + email);
        //System.out.println("Login attempt:Password = " + password);

        boolean result = DBFacade.authenticateUser(email,password);

        resultMap.put("success", result);

        Gson gson = new Gson();
        String response = gson.toJson(resultMap);

        return Response.ok(response).build();
    }

    @POST
    @Path("userById")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUserById(@QueryParam("email") int id) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();

        UserEntity result = DBFacade.getUserById(id);
        result.setPassword("null");

        Gson gson = new Gson();
        String response = gson.toJson(result);

        return Response.ok(response).build();
    }
}
