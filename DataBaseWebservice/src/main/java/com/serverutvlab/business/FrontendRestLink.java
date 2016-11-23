package com.serverutvlab.business;

import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Created by o_0 on 2016-11-22.
 */
public class FrontendRestLink {
    private final static String backendAddress[] = {"http://localhost:8080/"};

    public static String[] getFrontendAddresses()
    {
        return backendAddress;
    }

    /**
     * doRestCall make a rest call to address to backend + path + service
     * ex: path = business/businessservice/ , serviceName = users
     * -> http://localhost:8090/ +  business/businessservice/ + users
     * @param path the path to the service
     * @param serviceName the name of the service
     * @return returns a string data of it
     */
    public static String doRestPost(String serverAddress, String path, String serviceName,String jsonString) {
        Client restClient = null;

        restClient = Client.create();
        WebResource webRes = restClient.resource(serverAddress + path + serviceName);
        System.out.println("");
        System.out.println("addr: " + serverAddress + path + serviceName);
        ClientResponse resp =  webRes.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,jsonString);

        int status = resp.getStatus();
        if (status != 200) {
            System.out.println("Warning; FrontendRestLink::doRestPost:: status code: " +status );
            return "";
        }
        String data = resp.getEntity(String.class);
        return data;
    }

    /**
     * doRestCall make a rest call to address to backend + path + service
     * ex: path = business/businessservice/ , serviceName = users
     * -> http://localhost:8080/ +  business/businessservice/ + users
     * @param path the path to the service
     * @param serviceName the name of the service
     * @return returns a string data of it
     */
    public static String doRestGet(String serverAddress, String path, String serviceName) {
        Client restClient = null;

        restClient = Client.create();
        WebResource webRes = restClient.resource(serverAddress + path + serviceName);
        ClientResponse resp = webRes.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        int status = resp.getStatus();
        if (status != 200) {
            System.out.println("Warning; FrontendRestLink::doRestCall:: status code: " +status );
            return "";
        }
        String data = resp.getEntity(String.class);
        return data;
    }
}
