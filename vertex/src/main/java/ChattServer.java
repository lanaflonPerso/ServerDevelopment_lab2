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
                if (ws.path().equals("/myapp")) {
                    ws.handler(new Handler<Buffer>() {
                        @Override
                        public void handle(Buffer buffer) {
                            System.out.println("hanterar request for myapp");
                            System.out.println("Datalast:  " + buffer.toString());
                            ws.writeFinalTextFrame(buffer.toString()); // Echo it back
                        }
                    });
                } else if (ws.path().equals("/messagesByGroup")) {
                    ws.handler(new Handler<Buffer>() {
                        @Override
                        public void handle(Buffer buffer) {
                            System.out.println("hanterar request for messages");
                            System.out.println("Datalast:  " + buffer.toString());
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
