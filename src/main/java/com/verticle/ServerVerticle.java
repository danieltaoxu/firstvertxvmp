package com.verticle;

import com.dbComponent.MongoDBComponent;
import com.models.Dish;
import com.models.EntityWithID;
import com.server.HttpServerResponseCode;
import com.server.HttpServerService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;


public class ServerVerticle extends AbstractVerticle{

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        //Future<Void> steps = MongoDBComponent.getSingletonComponent().prepareDB(vertx).compose(v -> HttpServerService.httpServerServiceSingleton().startServer());
        //steps.setHandler(startFuture.completer());
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {

    }



    private void creationHandler(RoutingContext routingContext) {

    }

    private void updateHandler(RoutingContext routingContext) {
        final String id = routingContext.request().getParam("dishID");
        JsonObject json = routingContext.getBodyAsJson();
        if(id == null || json == null) {
            routingContext.response().setStatusCode(400).end();
        } else {
            MongoClient mongoClient = MongoDBComponent.getSingletonComponent().prepareDB(vertx);
            mongoClient.updateCollection("Dishes", new JsonObject().put("_id", id),
                new JsonObject().put("$set", json), ar -> {
                if (ar.failed()) routingContext.response().setStatusCode(HttpServerResponseCode.NOT_FOUND.getCode()).end();
                else {
                    routingContext.response().putHeader("content-type", "application/json; utf-8")
                    .end(Json.encodePrettily(new Dish(Double.parseDouble(json.getString("price")), json.getString("name"), Long.parseLong(id))));
                }
            });
        }
    }

    private void deleteHandler(RoutingContext routingContext) {

    }

    private void indexHandler(RoutingContext routingContext)
    {

    }

    private Router configRouter()
    {
        Router router = Router.router(vertx);
        router.get("/").handler(this::indexHandler);
        router.post("/dishes/save/:dishID").handler(this::updateHandler);
        router.put("/dishes/create").handler(this::creationHandler);
        router.delete("/dishes/delete/:dishID").handler(this::deleteHandler);
        return router;
    }

    public Future<Void> startHttpServer()
    {
        Future<Void> httpServerFuture = Future.future();
        HttpServer httpServer = vertx.createHttpServer();
        Router router = configRouter();
        httpServer.requestHandler(router::accept).listen(8080, ar -> {
            if (ar.succeeded()) httpServerFuture.complete();
            else httpServerFuture.fail(ar.cause());
        });
        return httpServerFuture;
    }

}
