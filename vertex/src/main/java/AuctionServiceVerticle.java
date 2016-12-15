import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.ErrorHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

import static io.vertx.ext.web.handler.sockjs.BridgeEvent.Type.SOCKET_CREATED;

/**
 * Created by cj on 2016-12-14.
 */
public class AuctionServiceVerticle extends AbstractVerticle {

    @Override
    public void start() {
        System.out.println("starting server...");

        Router router = Router.router(vertx);

        router.route("/eventbus/*").handler(eventBusHandler());
        router.mountSubRouter("/api", auctionApiRouter());
        router.route().failureHandler(errorHandler());
        router.route().handler(staticHandler());

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

        System.out.println("server running on port 8080...");
    }

    //…
    private ErrorHandler errorHandler() {
        return ErrorHandler.create();
    }

    private SockJSHandler eventBusHandler() {
        BridgeOptions options = new BridgeOptions()
                .addOutboundPermitted(new PermittedOptions().setAddressRegex("messages"));
        return SockJSHandler.create(vertx).bridge(options, event -> {
            if (event.type() == SOCKET_CREATED) {
                System.out.println("A socket was created");
            }
            event.complete(true);
        });
    }

    private Router auctionApiRouter() {
        //AuctionRepository repository = new AuctionRepository(vertx.sharedData());


        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        router.route().consumes("application/json");
        router.route().produces("application/json");

        //router.get("/messageByGroup/:id").handler(Handlers::handleGetMessagesByGroup);

        return router;
    }





    private StaticHandler staticHandler() {
        return StaticHandler.create()
                .setCachingEnabled(false);
    }
}
