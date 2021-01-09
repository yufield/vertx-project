package com.yufield.post

import io.vertx.core.Vertx
import io.vertx.ext.web.client.WebClient
import io.vertx.junit5.VertxExtension
import io.vertx.kotlin.core.json.jsonObjectOf
import io.vertx.kotlin.coroutines.await
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

private val logger = KotlinLogging.logger {}

@ExtendWith(VertxExtension::class)
class DatabasePrepare {

  @Test
  fun prepareCities(vertx: Vertx) {
    val client = WebClient.create(vertx)
    runBlocking {
      val pair = jsonObjectOf(
        "asdf" to "asdaf"
      )
      val response = client.put(9200, "localhost", "/post/_doc/2").sendJson(pair).await()
      logger.info { response.body().toString() }
    }
  }
}
