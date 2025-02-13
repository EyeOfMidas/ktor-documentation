[//]: # (title: Streaming)

<include src="lib.xml" include-id="outdated_warning"/>

Most of the response types are complete in memory. But you can also fetch streaming data as well.

## Scoped streaming

There are multiple ways of doing streaming. The safest way is using [HttpStatement](https://api.ktor.io/ktor-client/ktor-client-core/ktor-client-core/io.ktor.client.statement/-http-statement/index.html) with scoped `execute` block:

```kotlin
client.get<HttpStatement>.execute { response: HttpResponse ->
    // Response is not downloaded here.
    val channel = response.receive<ByteReadChannel>()
}
```

After `execute` block is finished, network resources is released.

You can also point different type for `execute` method:

```kotlin
client.get<HttpStatement>.execute<ByteReadChannel> { channel: ByteReadChannel ->
    // ...
}
```