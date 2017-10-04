package com.dbComponent;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

public class MongoDBComponent implements DBComponent{

    private MongoDBComponent(){}

    public static MongoDBComponent getSingletonComponent()
    {
        return mongoDBComponent == null ? (mongoDBComponent = new MongoDBComponent()) : mongoDBComponent;
    }

    public MongoClient prepareDB(Vertx vertx) {
        String uri = "mongodb://localhost:27017";
        String db = "Vertx";
        JsonObject mongodbObject = new JsonObject()
                .put("uri_string", uri)
                .put("db_name", db)
                .put("max_pool, size", 30);
        return MongoClient.createShared(vertx, mongodbObject);
    }



    private static MongoDBComponent mongoDBComponent = null;
}
