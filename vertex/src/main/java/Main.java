import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

/**
 * Created by cj on 2016-12-05.
 */
public class Main {
    public static void main(String[] args) {

        DeploymentOptions options = new DeploymentOptions().setWorker(true);
        //Vertx.vertx().deployVerticle(new Server(),options);
        Vertx.vertx().deployVerticle(new ChattServer(),options);

    }
}
