package com.example.starter;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

@ProxyGen
@VertxGen
public interface SomeDatabaseService {
  void save(String collection, JsonObject document,
            Handler<AsyncResult<Void>> result);

  void findOne(String collection, JsonObject query,
               Handler<AsyncResult<JsonObject>> result);
}
