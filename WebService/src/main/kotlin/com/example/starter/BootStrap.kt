package com.example.starter

import io.vertx.core.Vertx

fun main() {
  Vertx.vertx().deployVerticle(MainVerticle::class.java.name)
}
