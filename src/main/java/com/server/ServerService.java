package com.server;

import io.vertx.core.http.HttpServer;

import io.vertx.core.Future;

public interface ServerService {
    public Future<Void> startServer();
    public HttpServer createServerService();
}
