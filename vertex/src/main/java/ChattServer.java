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

    @Override

    public void start() {
        EventBus eb = vertx.eventBus();
        ConcurrentHashMap<Integer,ServerWebSocket> clients = new ConcurrentHashMap<>();

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
                                eb.send(request, buffer.toString());


                            } else if (request.equals("sendMessageToGroup")){
                                System.out.println("processing sendMessageToUser");
                                eb.send(request, buffer.toString());


                            } else {


                            }
                            System.out.println("end of the world! ");

                            ws.writeFinalTextFrame(buffer.toString()); // Echo it back
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
            System.out.println("Result: " + result);
            eb.send("sendBack-"+result.getString("request"), result.toString());
        });
        eb.consumer("sendMessageToUser", data -> {
            JsonObject object = new JsonObject(data.body().toString());
            System.out.println("I have began to process ::sendMessageToUser:: request = " + object);
            JsonObject result = Handlers.handleSendMessageToUser(object);
            System.out.println("Result: " + result);
            eb.send("sendBack-"+result.getString("request"), result.toString());
        });
        eb.consumer("sendMessageToGroup", data -> {
            JsonObject object = new JsonObject(data.body().toString());
            System.out.println("I have began to process ::sendMessageToGroup:: request = " + object);
            JsonObject result = Handlers.handleSendMessageToGroup(object);
            System.out.println("Result: " + result);
        });

        eb.consumer("sendBack-getMessagesBetweenUsers", data -> {
            JsonObject result = new JsonObject(data.body().toString());

            ServerWebSocket s1 = clients.get(result.getInteger("fromId"));

            s1.writeFinalTextFrame(result.toString());

        });

        eb.consumer("sendBack-sendMessageToUser", data -> {
            JsonObject result = new JsonObject(data.body().toString());

            ServerWebSocket s1 = null;
            ServerWebSocket s2 = null;

            s1 = clients.get(result.getInteger("fromId"));
            s2 = clients.get(result.getInteger("toId"));

            if (s1 != null)
                s1.writeFinalTextFrame(result.toString());
            if (s2 != null)
                s2.writeFinalTextFrame(result.toString());

        });


    }
}
