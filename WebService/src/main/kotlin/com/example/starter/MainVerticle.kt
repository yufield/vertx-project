package com.example.starter

import io.vertx.ext.web.Router
import io.vertx.kotlin.core.http.listenAwait
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await
import io.vertx.kotlin.pgclient.pgConnectOptionsOf
import io.vertx.kotlin.sqlclient.poolOptionsOf
import io.vertx.pgclient.PgPool
import kotlinx.coroutines.launch
import org.jooq.SQLDialect
import org.jooq.generated.tables.daos.CitiesDao
import org.jooq.impl.DefaultConfiguration

class MainVerticle : CoroutineVerticle() {

  override suspend fun start() {
    val pgPool = PgPool.pool(vertx,
      pgConnectOptionsOf(
        host = "localhost", port = 5432, user = "postgres", password = "admin", database = "postgres"
      ), poolOptionsOf())
    val citiesDao = CitiesDao(DefaultConfiguration().set(SQLDialect.POSTGRES), pgPool)
    val router =
      Router.router(vertx).also { router ->
        router.get().handler { event ->
          launch {
            val await = citiesDao.findOneById(1).await()
            event.response().end(await.toString())
          }
        }
      }

    val httpServer = vertx.createHttpServer()
    httpServer.requestHandler(router).listenAwait(8888)
    println("HTTP server started on port ${httpServer.actualPort()}")
  }
}

