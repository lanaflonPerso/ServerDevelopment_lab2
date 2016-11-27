package backend;

import chat.ChatMessageVM;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestBackendLink {
    private final static String backendAddress = "http://localhost:8090/";

    public static boolean sendChatMessage(ChatMessageVM chatMessageVM) {
        System.out.println("RestBackendLink::sendChatMessage");
        //ChatMessageVM chatMessageVM = new ChatMessageVM("the message", "this is me", "recive it dude");
        String s = new Gson().toJson(chatMessageVM, ChatMessageVM.class);
        System.out.println("Sending json string: " + s);

        String s1 = RestBackendLink.doRestJsonPost("services/chatService/","sendmessage",s);
        System.out.println("Response is: " + s1);
        return true;
    }

    public static boolean sendChatRequest(ChatMessageVM chatMessageVM) {
        System.out.println("RestBackendLink::sendChatRequest");
        //ChatMessageVM chatMessageVM = new ChatMessageVM("the message", "this is me", "recive it dude");
        String s = new Gson().toJson(chatMessageVM, ChatMessageVM.class);
        System.out.println("Sending json string: " + s);

        String s1 = RestBackendLink.doRestJsonPost("services/chatService/","chatRequest",s);
        System.out.println("Response is: " + s1);
        return true;
    }

    /**
     * doRestGet make a rest call to address to backend + path + service
     * ex: path = business/businessservice/ , serviceName = users
     * -> http://localhost:8090/ +  business/businessservice/ + users
     * @param path the path to the service
     * @param serviceName the name of the service
     * @return returns a string data of it
     */
    public static String doRestJsonPost(String path, String serviceName, String jsonString) {
        Client restClient = null;

        restClient = Client.create();
        WebResource webRes = restClient.resource(backendAddress + path + serviceName);
        System.out.println("add: " + backendAddress + path + serviceName);
        ClientResponse resp =  webRes.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,jsonString);

        int status = resp.getStatus();
        if (status != 200) {
            System.out.println("Warning; DataConn.RestBackendLink::doRestGet:: status code: " +status );
            return "";
        }
        String data = resp.getEntity(String.class);
        return data;
    }

    /**
     * doRestGet make a rest call to address to backend + path + service
     * ex: path = business/businessservice/ , serviceName = users
     * -> http://localhost:8090/ +  business/businessservice/ + users
     * @param path the path to the service
     * @param serviceName the name of the service
     * @return returns a string data of it
     */
    public static String doRestParmPost(String path, String serviceName,Map<String,Object> params) {
        String parameters = prepareParams(params);

        Client restClient = null;
        restClient = Client.create();
        WebResource webRes = restClient.resource(backendAddress + path + serviceName + parameters);
        System.out.println("add: " + backendAddress + path + serviceName + parameters);
        ClientResponse resp =  webRes.type(MediaType.TEXT_PLAIN).post(ClientResponse.class);

        int status = resp.getStatus();
        switch (status){
            case 200:
                return resp.getEntity(String.class);
            case 401:
                return "null"; // Unauthorized
            default:
                System.out.println("Warning; DataConn.RestBackendLink::doRestGet:: status code: " +status );
                return "null"; // Bad request
        }
    }

    /**
     * doRestGet make a rest call to address to backend + path + service
     * ex: path = business/businessservice/ , serviceName = users
     * -> http://localhost:8090/ +  business/businessservice/ + users
     * @param path the path to the service
     * @param serviceName the name of the service
     * @return returns a string data of it
     */
    public static String doRestGet(String path, String serviceName) {
        Client restClient = null;

        restClient = Client.create();
        WebResource webRes = restClient.resource(backendAddress + path + serviceName);

        ClientResponse resp = webRes.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        int status = resp.getStatus();
        if (status != 200) {
            System.out.println("Warning; DataConn.RestBackendLink::doRestGet:: status code: " +status );
            return "";
        }
        String data = resp.getEntity(String.class);
        return data;
    }

    /**
     * doRestGet make a rest call to address to backend + path + service
     * ex: path = business/businessservice/ , serviceName = users
     * -> http://localhost:8090/ +  business/businessservice/ + users
     * @param path the path to the service
     * @param serviceName the name of the service
     * @return returns a string data of it
     */
    public static String doRestGet(String path, String serviceName,Map<String,Object> params) {
        String parameters = prepareParams(params);
        Client restClient = null;
        restClient = Client.create();
        WebResource webRes = restClient.resource(backendAddress + path + serviceName + parameters);

        ClientResponse resp = webRes.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        int status = resp.getStatus();
        if (status != 200) {
            System.out.println("Warning; DataConn.RestBackendLink::doRestGet:: status code: " +status );
            return "";
        }
        String data = resp.getEntity(String.class);
        return data;
    }


    /**
     * parsing json to the type given
     * @param type
     * @param data
     * @param <T>
     * @return
     */
    public static <T> T parseJsonData(Type type, String data) {
        Gson gson = new Gson();
        T result = gson.fromJson(data, type);
        return result;
    }

    /**
     * prepares the parameters in the Map and concatenates the parameters into a long line
     * @param parameters
     * @return
     */
    public static String prepareParams(Map<String,Object> parameters){
        StringBuilder params = new StringBuilder();
        try{

        for (Map.Entry<String,Object> p: parameters.entrySet()){
            if (params.length() == 0)
                params.append('?');
            else
                params.append('&');

            params.append(URLEncoder.encode(p.getKey(),"UTF-8"));
            params.append('=');
            params.append(URLEncoder.encode(String.valueOf(p.getValue()), "UTF-8"));
        }

        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return null;
        }

        return params.toString();
    }


}