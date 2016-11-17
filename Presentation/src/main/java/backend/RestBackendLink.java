package backend;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RestBackendLink {
    public static String getUsers()
    {
        Client restClient = Client.create();

        WebResource webRes = restClient.resource("http://localhost:8090/business/businessservice/users");
        ClientResponse resp =  webRes.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        int status = resp.getStatus();
        System.out.println("DataConn.RestBackendLink::getUsers:: status code: " +status );
        if (status != 200) {
            return "error: code: " + status;
        }
        String data = resp.getEntity(String.class);

        return data;
    }

    public static List<UserEntity> getUsersAll()
    {
        Client restClient = Client.create();

        WebResource webRes = restClient.resource("http://localhost:8090/business/businessservice/users");
        ClientResponse resp =  webRes.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        int status = resp.getStatus();
        System.out.println("DataConn.RestBackendLink::getUsers:: status code: " +status );
        if (status != 200) {
            return new ArrayList<UserEntity>();
        }
        String data = resp.getEntity(String.class);
        //Gson gson = new Gson();
        Type arrType = new TypeToken<ArrayList<UserEntity>>(){}.getType();
        List<UserEntity> list = new Gson().fromJson(data, arrType);
        return list;
    }
}