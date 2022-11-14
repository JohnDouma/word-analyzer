import io.vertx.core.*;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * Main entry point into the app. Creates and deploys an AnalyzeVerticle to handle
 * POST /analyze
 */
public class MainVerticle extends AbstractVerticle {
    public static void main(String[] args) {
        Launcher.executeCommand("run", MainVerticle.class.getName());
    }

    @Override
    public void start() throws Exception {
        vertx.deployVerticle(new AnalyzeVerticle());
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.post("/analyze").handler(context -> {
            vertx.eventBus().request(AnalyzeVerticle.ANALYZE, context.body().asJsonObject(), reply -> {
                context.request().response().end(reply.result().body().toString());
            });
        });

        // Create the HTTP server
        vertx.createHttpServer()
                // Handle every request using the router
                .requestHandler(router)
                // Start listening
                .listen(8080)
                // Print the port
                .onSuccess(server ->
                        System.out.println(
                                "HTTP server started on port " + server.actualPort()
                        )
                );
    }
}
