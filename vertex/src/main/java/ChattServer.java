import DBLayer.MessageDAO;
import io.vertx.core.AbstractVerticle;

import io.vertx.core.Handler;
import io.vertx.core.Verticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;

import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.json.JsonObject;


/**
 * Created by cj on 2016-12-14.
 */
public class ChattServer extends AbstractVerticle {

    @Override

    public void start() {

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

                            if (request.equals("getMessagesBetweenUsers")){
                                System.out.println("processing getMessagesBetweenUsers");


                            } else if (request.equals("getMessagesByGroup")){
                                System.out.println("processing getMessagesByGroup");


                            } else if (request.equals("getMessagesByGroup")){
                                System.out.println("processing getMessagesByGroup");


                            } else {


                            }

                            ws.writeFinalTextFrame(buffer.toString()); // Echo it back
                        }
                    });
                } else {
                    ws.reject();
                }
            }
        }).listen(8085);

        EventBus eb = vertx.eventBus();

        eb.consumer("messages", message -> {
            System.out.println("I have received a message: " + message.body());
        });

        eb.send("messages", "Yay! Someone kicked a ball");

    }
}
