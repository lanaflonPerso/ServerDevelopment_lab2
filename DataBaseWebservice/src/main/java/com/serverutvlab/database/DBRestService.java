package com.serverutvlab.database;

import com.serverutvlab.business.BModels.BUser;
import com.serverutvlab.database.DBLayer.DBFacade;
import com.serverutvlab.database.DBModels.UserEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by o_0 on 2016-11-14.
 */
@Path("dbrestservice")
public class DBRestService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello()
    {
        StringBuilder sb = new StringBuilder("");
        List<BUser> allUsers = DBFacade.getAllUsers();
        for (BUser ue: allUsers) {
            sb.append(ue.toString() + "\n");
        }
        return sb.toString();
    }
}
