[//]: # (title: Redirect)

<include src="lib.md" include-id="outdated_warning"/>

By default, Ktor HTTP client does follow redirections; this feature allows to follow `Location` redirects in a way that works with any HTTP engine. Its usage is pretty straightforward, and the only configurable property is the `maxJumps` (20 by default) that limits how many redirects are tried before giving up (to prevent infinite redirects).



## Install

This feature is installed by default.

## Prevent installing

```kotlin
val client = HttpClient(HttpClientEngine) {
    followRedirects = false
}
```

>This feature is included in the core of the HttpClient so it is available always along the client.
>
{type="note"}