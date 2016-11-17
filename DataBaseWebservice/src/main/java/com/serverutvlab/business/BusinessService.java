package com.serverutvlab.business;

        import com.google.gson.Gson;
        import com.serverutvlab.database.DBLayer.DBFacade;
        import com.serverutvlab.database.DBModels.UserEntity;

        import javax.ws.rs.*;
        import javax.ws.rs.core.MediaType;
        import javax.ws.rs.core.Response;
        import java.util.List;

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
        List<UserEntity> allUsers = DBFacade.getAllUsers();
        Gson gson = new Gson();
        String json = gson.toJson(allUsers);

        return json;
    }

    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sendEmail(@FormParam("email") String email, @FormParam("password") String password) {
        System.out.println("Login attempt:Email = " + email);
        System.out.println("Login attempt:Password = " + password);

        return Response.ok("email= " + email + ", password= " + password).build();
    }
}
