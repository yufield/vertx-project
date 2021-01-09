package com.yufield.post

import io.vertx.core.Vertx

fun main() {
  Vertx.vertx().deployVerticle(MainVerticle())
}
