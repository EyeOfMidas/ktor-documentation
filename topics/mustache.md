[//]: # (title: Mustache)
[mustache_factory]: http://spullara.github.io/mustache/apidocs/com/github/mustachejava/MustacheFactory.html

Ktor allows you to use [Mustache templates](https://github.com/spullara/mustache.java) as views within your application by installing the [Mustache](https://api.ktor.io/%ktor_version%/io.ktor.mustache/-mustache/index.html) feature.


## Add Dependencies {id="add_dependencies"}
<var name="feature_name" value="Mustache"/>
<var name="artifact_name" value="ktor-mustache"/>
<include src="lib.md" include-id="add_ktor_artifact_intro"/>
<include src="lib.md" include-id="add_ktor_artifact"/>

## Install Mustache {id="install_feature"}

<var name="feature_name" value="Mustache"/>
<include src="lib.md" include-id="install_feature"/>

Inside the `install` block, you can [configure](#template_loading) the [MustacheFactory][mustache_factory] for loading Mustache templates.


## Configure Mustache {id="configure"}
### Configure Template Loading {id="template_loading"}
To load templates, you need to assign the [MustacheFactory][mustache_factory] to the `mustacheFactory` property. For example, the code snippet below enables Ktor to look up templates in the `templates` package relative to the current classpath:
```kotlin
import io.ktor.mustache.*

install(Mustache) {
    mustacheFactory = DefaultMustacheFactory("templates")
}
```

### Send a Template in Response {id="use_template"}
Imagine you have the `index.hbs` template in `resources/templates`:
```html
<html>
    <body>
        <h1>Hello, {{user.name}}</h1>
    </body>
</html>
```

A data model for a user looks as follows:
```kotlin
data class User(val id: Int, val name: String)
```

To use the template for the specified [route](Routing_in_Ktor.md), pass `MustacheContent` to the `call.respond` method in the following way:
```kotlin
get("/index") {
    val sampleUser = User(1, "John")
    call.respond(MustacheContent("index.hbs", mapOf("user" to sampleUser)))
}
```
