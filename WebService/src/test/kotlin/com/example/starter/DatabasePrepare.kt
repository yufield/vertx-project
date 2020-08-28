package com.example.starter

import io.vertx.core.Vertx
import io.vertx.kotlin.coroutines.await
import io.vertx.kotlin.coroutines.dispatcher
import io.vertx.kotlin.pgclient.pgConnectOptionsOf
import io.vertx.kotlin.sqlclient.poolOptionsOf
import io.vertx.pgclient.PgPool
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jooq.SQLDialect
import org.jooq.generated.tables.daos.CitiesDao
import org.jooq.generated.tables.pojos.Cities
import org.jooq.impl.DefaultConfiguration
import org.junit.jupiter.api.Test

class DatabasePrepare {
  val vertx = Vertx.vertx()
  val pgPool = PgPool.pool(vertx,
    pgConnectOptionsOf(
      host = "localhost", port = 5432, user = "postgres", password = "admin", database = "postgres"
    ), poolOptionsOf())
  val citiesDao = CitiesDao(DefaultConfiguration().set(SQLDialect.POSTGRES), pgPool)

  @Test
  fun prepareCities() {
    GlobalScope.launch(vertx.dispatcher()) {
      for (i in 2..100) {
        citiesDao.insert(Cities(i, "no $i citiy")).await()
      }
    }
  }
}
