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
    private final static String backendAddress = "http://localhost:8090/";

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
        Gson gson = new Gson();
        Type arrType = new TypeToken<ArrayList<UserEntity>>(){}.getType();
        List<UserEntity> list = gson.fromJson(data, arrType);
        return list;
    }

    /**
     * doRestCall make a rest call to address to backend + path + service
     * ex: path = business/businessservice/ , serviceName = users
     * -> http://localhost:8090/ +  business/businessservice/ + users
     * @param path the path to the service
     * @param serviceName the name of the service
     * @return returns a string data of it
     */
    public static String doRestCall(String path, String serviceName) {
        Client restClient = null;

        restClient = Client.create();
        WebResource webRes = restClient.resource(backendAddress + path + serviceName);
        ClientResponse resp = webRes.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        int status = resp.getStatus();
        if (status != 200) {
            System.out.println("Warning; DataConn.RestBackendLink::doRestCall:: status code: " +status );
            return "";
        }
        String data = resp.getEntity(String.class);
        return data;
    }

    public static <T> T parseJsonData(Type type, String data) {
        Gson gson = new Gson();
        T result = gson.fromJson(data, type);
        return result;
    }
}