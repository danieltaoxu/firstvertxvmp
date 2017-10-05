package com.verticle;

import com.dbComponent.MongoDBComponent;
import com.factory.DataFactory;
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

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;


public class ServerVerticle extends AbstractVerticle{

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        startHttpServer();
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
                    .end(Json.encodePrettily(new Dish(Double.parseDouble(json.getString("price")), json.getString("name"), id)));
                }
            });
        }
    }

    private void deleteHandler(RoutingContext routingContext) {

    }

    private void indexHandler(RoutingContext routingContext)
    {
        MongoClient mongoClient = MongoDBComponent.getSingletonComponent().prepareDB(vertx);
        mongoClient.find("Dishes", new JsonObject(), results -> {
            List<JsonObject> objects = results.result();
            List<Dish> dishes = objects
                .stream()
                .map(jsonOj -> createData(Dish.class, jsonOj))
                .collect(Collectors.toList());
            /*List<Dish> dishes = new Vector<>();
            for (JsonObject json : objects)
            {
                JsonObject id = (JsonObject) json.getValue("_id");

                System.out.println(id.getString("$oid"));
                System.out.println(json.getString("name"));
                System.out.println(json.getDouble("price"));
            }*/
            routingContext.response().putHeader("content-type", "application/json; utf-8").end(Json.encodePrettily(dishes));
        });
    }

    private <Type extends EntityWithID> Type createData(Class<Type> anEntityClass, JsonObject aJson)
    {

        return DataFactory.createEntityWithArguments(anEntityClass, aJson.getDouble("price").doubleValue(), aJson.getString("name"), ((JsonObject) aJson.getValue("_id")).getString("$oid"));
    }

    private Router configRouter()
    {
        Router router = Router.router(vertx);
        router.get("/").handler(this::indexHandler);
        router.get("/dishes/:dishID").handler(this::indexHandler);
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
