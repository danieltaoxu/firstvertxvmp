package com.dbComponent;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class DBComponentManager {
    public static <DBClient> DBClient createDatabaseClient(DBClient dbClient, Vertx vertx, JsonObject aConfigJson, Class<? extends Object> aDbClientClass)
    {
        return dbClient;
    }
}
