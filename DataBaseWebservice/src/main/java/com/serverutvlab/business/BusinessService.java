package com.serverutvlab.business;

        import com.google.gson.Gson;
        import com.serverutvlab.database.DBLayer.DBFacade;
        import com.serverutvlab.database.DBLayer.Staff;
        import com.serverutvlab.database.DBModels.UserEntity;

        import javax.ws.rs.GET;
        import javax.ws.rs.Path;
        import javax.ws.rs.Produces;
        import javax.ws.rs.core.MediaType;
        import java.util.ArrayList;
        import java.util.Collection;
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
        StringBuilder sb = new StringBuilder("");
        List<UserEntity> allUsers = DBFacade.getAllUsers();
        for (UserEntity ue: allUsers) {
            sb.append(ue.toString() + "\n");
        }
        return sb.toString();
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("test")
    public String testGson()
    {
        Collection<Staff> arr = new ArrayList<Staff>();
        for(int i= 0; i<2;i++){
            arr.add(createDummyObject());
        }

        Gson gson = new Gson();
        String json = gson.toJson(arr);
        System.out.println(json);


        return json.toString();
    }



    private Staff createDummyObject() {

        Staff staff = new Staff();

        staff.setName("mkyong");
        staff.setAge(35);
        staff.setPosition("Founder");

        List<String> skills = new ArrayList<String>();
        skills.add("java");
        skills.add("python");
        skills.add("shell");

        staff.setSkills(skills);

        return staff;

    }





}
