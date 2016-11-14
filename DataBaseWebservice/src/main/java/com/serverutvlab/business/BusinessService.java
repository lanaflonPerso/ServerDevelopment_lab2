package com.serverutvlab.business;

        import com.serverutvlab.database.DBLayer.DBFacade;
        import com.serverutvlab.database.DBModels.UserEntity;

        import javax.ws.rs.GET;
        import javax.ws.rs.Path;
        import javax.ws.rs.Produces;
        import javax.ws.rs.core.MediaType;
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
}
