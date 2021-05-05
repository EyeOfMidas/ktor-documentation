package io.ktor.snippets.jsonclient

import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.shared.serializaion.gson.*
import kotlinx.coroutines.*

data class Model(val name: String, val items: List<Item>)
data class Item(val key: String, val value: String)

fun main(args: Array<String>) = runBlocking<Unit> {
    val client = HttpClient(Apache) {
        install(ContentNegotiation) {
            gson()
        }
    }
    println("Requesting model...")
    val model = client.get<Model>(port = 8080, path = "/v1")
    println("Fetching items for '${model.name}'...")
    for ((key, _) in model.items) {
        val item = client.get<Item>(port = 8080, path = "/v1/item/$key")
        println("Received: $item")
    }
}
