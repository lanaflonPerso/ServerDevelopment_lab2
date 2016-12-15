import DBLayer.MessageDAO;
import io.vertx.core.AbstractVerticle;

import io.vertx.core.Handler;
import io.vertx.core.Verticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;

import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by cj on 2016-12-14.
 */
public class ChattServer extends AbstractVerticle {
    ConcurrentHashMap<Integer,ServerWebSocket> clients = new ConcurrentHashMap<>();

    public void sendMessageP2P(JsonObject data) {
        Integer fromId = data.getInteger("fromId");
        Integer toUser = data.getInteger("toId");
        System.out.println("sendMessageP2P: fromId = " + fromId + " to" + toUser);
        if (fromId != null) {
            ServerWebSocket fromWs = clients.get(fromId);
            if (fromWs != null) {
                fromWs.writeFinalTextFrame(data.toString());

            }
        }

        if (toUser != null) {
            ServerWebSocket toWs = clients.get(toUser);
            if (toWs != null) {
                toWs.writeFinalTextFrame(data.toString());
            }

        }
    }

    @Override
    public void start() {
        EventBus eb = vertx.eventBus();


        vertx.createHttpServer().websocketHandler(new Handler<ServerWebSocket>() {

            public void handle(final ServerWebSocket ws) {
                if (ws.path().equals("/chatserver")) {
                    ws.handler(new Handler<Buffer>() {
                        @Override
                        public void handle(Buffer buffer) {
                            System.out.println("hanterar request for myapp");
                            System.out.println("Datalast:  " + buffer.toString());

                            JsonObject data = new JsonObject(buffer.toString());
                            String request = data.getString("request");
                            System.out.println("Request: "+ request);



                            if (request.equals("register")) {
                                System.out.println("processing register");
                                clients.put(data.getInteger("userId"), ws);


                            } else if(request.equals("getMessagesBetweenUsers")){
                                System.out.println("processing getMessagesBetweenUsers");
                                eb.send(request, buffer.toString());

                            } else if (request.equals("getMessagesByGroup")){
                                System.out.println("processing getMessagesByGroup");


                            } else if (request.equals("getGroups")){
                                System.out.println("processing getMessagesByGroup");


                            } else if (request.equals("sendMessageToUser")){
                                System.out.println("processing sendMessageToUser");
                                sendMessageP2P(data);
                                eb.send(request, buffer.toString());




                            } else if (request.equals("sendMessageToGroup")){
                                System.out.println("processing sendMessageToUser");
                                eb.send(request, buffer.toString());



                            } else {


                            }
                            System.out.println("end of the world! ");

                            //ws.writeFinalTextFrame(buffer.toString()); // Echo it back
                        }
                    });
                } else {
                    ws.reject();
                }
            }
        }).listen(8085);



        eb.consumer("getMessagesBetweenUsers", data -> {
            String datat = (String) data.body();
            JsonObject object = new JsonObject(data.body().toString());
            System.out.println("I have began to process ::getMessageBetweenUsers:: request = " + object);

            JsonObject result = Handlers.handleGetMessagesBetweenUsers(object);

            eb.send("sendBackRequest", result.toString());
        });

        eb.consumer("getMessagesByGroup", data -> {
            String datat = (String) data.body();
            JsonObject object = new JsonObject(data.body().toString());
            System.out.println("I have began to process ::getMessagesByGroup:: request = " + object);

            JsonObject result = Handlers.handleGetMessagesByGroup(object);

            eb.send("sendBackRequest", result.toString());
        });


        eb.consumer("sendMessageToUser", data -> {
            JsonObject object = new JsonObject(data.body().toString());
            System.out.println("I have began to process ::sendMessageToUser:: request = " + object);

            Handlers.handleSendMessageToUser(object);

        });
        eb.consumer("sendMessageToGroup", data -> {
            JsonObject object = new JsonObject(data.body().toString());
            System.out.println("I have began to process ::sendMessageToGroup:: request = " + object);
            Handlers.handleSendMessageToGroup(object);

        });

        eb.consumer("sendBackRequest", data -> {
            JsonObject result = new JsonObject(data.body().toString());

            ServerWebSocket s1 = null;
            Integer id = result.getInteger("fromId");

            if (id != null){
                s1 = clients.get(id);
                if (s1 != null){
                    s1.writeFinalTextFrame(result.toString());
                }
            }
        });
    }
}
