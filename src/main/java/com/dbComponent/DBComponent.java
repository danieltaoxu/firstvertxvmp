package com.dbComponent;

import io.vertx.core.Vertx;
import io.vertx.ext.mongo.MongoClient;

public interface DBComponent {
    public MongoClient prepareDB(Vertx vertx);
}
