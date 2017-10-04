package com.server;

import com.dbComponent.DBComponentManager;
import com.dbComponent.MongoDBComponent;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

public class HttpServerService {

    private HttpServerService(){}

    public Future<Void> startServer() {
        Future<Void> future = Future.future();
        return future;
    }

    public HttpServer createServerService(Vertx vertx) {
        MongoDBComponent.getSingletonComponent().prepareDB(vertx);
        return null;
    }

    public static HttpServerService httpServerServiceSingleton()
    {
        if (httpServerService == null) httpServerService = new HttpServerService();
        return httpServerService;
    }

    private static HttpServerService httpServerService = null;
}
