package com.yufield.services.post;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

@ProxyGen
public interface PostService {
  void save(String collection, JsonObject document, Handler<AsyncResult<Void>> result);

  void findOne(String collection, JsonObject query, Handler<AsyncResult<JsonObject>> result);
}
