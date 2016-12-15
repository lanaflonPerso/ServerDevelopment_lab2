import DBLayer.DBFacade;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * Created by cj on 2016-12-05.
 */
public class Server extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) throws Exception {
        System.out.println("server starting...");
        Router router = Router.router(vertx);

        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/html").end("<h1>Hello from my first Vert.x 3 application</h1>");
        });

        router.route("/messagesByGroup*").handler(BodyHandler.create());
        router.get("/messagesByGroup").handler(Handlers::handleGetMessagesByGroup);

        router.route("/messagesBetweenUsers*").handler(BodyHandler.create());
        router.get("/messagesBetweenUsers").handler(Handlers::handleGetMessagesBetweenUsers);

        router.route("/getGroups*").handler(BodyHandler.create());
        router.get("/getGroups").handler(Handlers::handleGetGroups);

        router.route("/messageToGroup*").handler(BodyHandler.create());
        router.post("/messageToGroup").handler(Handlers::handleSendMessageToGroup);

        router.route("/messageToUser*").handler(BodyHandler.create());
        router.post("/messageToUser").handler(Handlers::handleSendMessageToUser);
        EventBus eb = vertx.eventBus();
        eb.consumer("test.lala", new  Handler<Message<String>() {
            @Override
            public void handle(Message<String> message) {

            }
        });


        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        config().getInteger("http.port", 8080),
                        result -> {
                            if (result.succeeded()) {
                                fut.complete();
                            } else {
                                fut.fail(result.cause());
                            }
                        }
                );

        System.out.println("server started, listening on port 8080... ");
    }

}
