package com.dbComponent;

import io.vertx.core.Future;

public interface DBComponent {
    public Future<Void> prepareDB();
}
