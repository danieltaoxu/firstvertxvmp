package com.verticle;

import com.dbComponent.MongoDBComponent;
import com.server.HttpServerService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

import io.vertx.ext.web.Router;

public class ServerVerticle extends AbstractVerticle{

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Future<Void> steps = MongoDBComponent.getSingletonComponent().prepareDB().compose(v -> HttpServerService.httpServerServiceSingleton().startServer());
        steps.setHandler(startFuture.completer());
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {

    }

}
