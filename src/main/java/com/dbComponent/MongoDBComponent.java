package com.dbComponent;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class MongoDBComponent implements DBComponent {

    private MongoDBComponent(){}

    public static MongoDBComponent getSingletonComponent()
    {
        return mongoDBComponent == null ? (mongoDBComponent = new MongoDBComponent()) : mongoDBComponent;
    }

    public Future<Void> prepareDB() {
        Future<Void> future = Future.future();
        JsonObject config = Vertx.currentContext().config();

        String uri;
        return future;
    }

    private static MongoDBComponent mongoDBComponent = null;
}
