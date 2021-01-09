package com.yufield.post

import com.yufield.jooq.tables.daos.PostDao
import com.yufield.services.post.PostService
import io.vertx.core.AsyncResult
import io.vertx.core.Context
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.ResponseContentTypeHandler
import io.vertx.ext.web.handler.ResponseTimeHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await
import io.vertx.kotlin.pgclient.pgConnectOptionsOf
import io.vertx.kotlin.sqlclient.poolOptionsOf
import io.vertx.pgclient.PgPool
import mu.KotlinLogging
import org.jooq.SQLDialect
import org.jooq.impl.DefaultConfiguration

private val logger = KotlinLogging.logger {}

class MainVerticle : CoroutineVerticle(), PostService {
  private lateinit var pgPool: PgPool
  private lateinit var postDao: PostDao
  private lateinit var router: Router
  override fun init(vertx: Vertx, context: Context) {
    super.init(vertx, context)
    pgPool = PgPool.pool(
      vertx,
      pgConnectOptionsOf(
        host = "localhost", port = 5432, user = "postgres", password = "admin", database = "postgres"
      ), poolOptionsOf()
    )
    postDao = PostDao(DefaultConfiguration().set(SQLDialect.POSTGRES), pgPool)
    router = Router.router(vertx)
    router.route().handler(ResponseTimeHandler.create())
    router.route().handler(ResponseContentTypeHandler.create())
    router.get().produces("text").handler { it.response().send("hello") }

  }

  override suspend fun start() {
    val httpServer = vertx.createHttpServer()
    httpServer
      .requestHandler(router)
      .listen(8888).await()
    logger.info { "HTTP server started on port ${httpServer.actualPort()}" }
  }

  override fun save(collection: String?, document: JsonObject?, result: Handler<AsyncResult<Void>>?) {
    TODO("Not yet implemented")
  }

  override fun findOne(collection: String?, query: JsonObject?, result: Handler<AsyncResult<JsonObject>>?) {
    TODO("Not yet implemented")
  }
}

