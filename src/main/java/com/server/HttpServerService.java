package com.server;

import io.vertx.core.http.HttpServer;
import io.vertx.core.Future;

public class HttpServerService implements ServerService {

    private HttpServerService(){}

    public Future<Void> startServer() {
        Future<Void> future = Future.future();
        return future;
    }

    public HttpServer createServerService() {
        return null;
    }

    public static HttpServerService httpServerServiceSingleton()
    {
        if (httpServerService == null) httpServerService = new HttpServerService();
        return httpServerService;
    }

    private static HttpServerService httpServerService = null;
}
