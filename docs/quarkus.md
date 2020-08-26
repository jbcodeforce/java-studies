# Quarkus Summary and trick

Best source of knowledge is [reading the guides](https://quarkus.io/guides/) and the [workshop](https://quarkus.io/quarkus-workshops/)

## Value propositions

* Designed to run java for microservice in container and OpenShift: reduce start time to microseconds, and reduce memory footprint.
* Run native in linux based image, so most of the java processing is done at build time.
* Extensible components, Quarkus implements reactive programming with Vert.x


Quarkus HTTP support is based on a non-blocking and reactive engine (Eclipse Vert.x and Netty). All the HTTP requests your application receives, are handled by event loops (IO Thread) and then are routed towards the code that manages the request.

## Create a project

```shell
mvn io.quarkus:quarkus-maven-plugin:1.7.0.Final:create \
    -DprojectGroupId=ibm.gse.eda \
    -DprojectArtifactId=app-name \
    -DclassName="ibm.gse.eda.GreetingResource" \
    -Dpath="/greeting"
cd app-name
```

## Package & run

Run with automatic compilation `./mvnw compile quarkus:dev`.

Can be packaged using `./mvnw clean package` or `./mvnw clean package -Pnative` for native execution, you need a Graalvm installed locally, or use the command: `./mvnw package -Pnative -Dquarkus.native.container-build=true` to build with a docker build image. 
For native build see [QUARKUS - TIPS FOR WRITING NATIVE APPLICATIONS](https://quarkus.io/guides/writing-native-applications-tips)

Start and override properties at runtime:

`java -Dquarkus.datasource.password=youshallnotpass -jar target/myapp-runner.jar`

for a native executable: `./target/myapp-runner -Dquarkus.datasource.password=youshallnotpass`

Can also use environment variables: Environment variables names are following the [conversion rules of Eclipse MicroProfile](https://github.com/eclipse/microprofile-config/blob/master/spec/src/main/asciidoc/configsources.asciidoc#default-configsources).

### Debug within VSCode

Start debugger:  shift -> cmd -> P: `Quarkus:  Debug current Quarkus Project` to create a configuration.


### Other Maven quarkus cli

[See Maven tooling guide](https://quarkus.io/guides/maven-tooling)

```shell
# create a project
mvn io.quarkus:quarkus-maven-plugin:1.6.1.Final:create 
# getting extension
./mvnw quarkus:list-extensions
# Build native executable
./mvnw package -Pnative
# Run integration tests on native app
./mvnw verify -Pnative
```

## Add capabilities

Useful capabilities:

* **Heath** for liveness and readiness: `./mvnw quarkus:add-extension -Dextensions="smallrye-health"`
* **Metrics** for application monitoring: `./mvnw quarkus:add-extension -Dextensions="smallrye-metrics"`
* Use API over HTTP in the JSON format: `./mvnw quarkus:add-extension -Dextensions="resteasy-jsonb"`.
* **Openapi** and swagger-ui `./mvnw quarkus:add-extension -Dextensions="quarkus-smallrye-openapi"`. Also to get the swagger-ui visible in "production" set `quarkus.swagger-ui.always-include=true` in the application.properties.
* **Kafka** client: `./mvnw quarkus:add-extension -Dextensions="kafka"`

* **Kubernetes** to get the deployment yaml file generated
* Deploy to **Openshift** using source to image `./mvnw quarkus:add-extension -Dextensions="openshift"`.  See guide [QUARKUS - DEPLOYING ON OPENSHIFT](https://quarkus.io/guides/deploying-to-openshift)
* `./mvnw quarkus:add-extension -Dextensions="container-image-docker"`
* **vert.x**: `./mvnw quarkus:add-extension -Dextensions="vertx"`

### Docker build

`./mvnw clean package -Dnative -Dquarkus.container-image.build=true` and push it to repository: `./mvnw clean package -Dquarkus.container-image.push=true`

To avoid downloading all the maven jars while using multistage dockerfile and to keep the current executable started with `quarkus:dev` running on the same network as other dependent components, use a simple docker file for development that has java and maven:

```dockerfile
FROM maven:3.6.3-jdk-11

COPY pom.xml /home/
COPY src /home/src/

WORKDIR /home

CMD ["mvn", "compile", "quarkus:dev"]
```

And then start it, this way:

```shell
docker build -f Dockerfile-dev -t tmp-builder .
docker run --rm -p 8080:8080 -ti --network kafkanet -v ~/.m2:/root/.m2 tmp-builder
```

We can also combine this into a docker-compose [file like in the item-aggregator](https://github.com/jbcodeforce/eda-kconnect-lab/blob/master/item-aggregator/docker-compose-dev.yaml) project.

```yaml
  maven:
    image: maven
    volumes:
      - "./:/app"
      - "~/.m2:/root/.m2"
    depends_on:
      - kafka
    hostname: aggregator
    environment:
      - BOOTSTRAP_SERVERS=kafka:9092
      - QUARKUS_KAFKA_STREAMS_BOOTSTRAP_SERVERS=kafka:9092
      - QUARKUS_PROFILE=dev
    ports:
      - "8080:8080"
      - "5005:5005"
    working_dir: /app
    command: "mvn quarkus:dev"
```


## Running on OpenShift

The guide is [here](https://quarkus.io/guides/deploying-to-openshift) and the main points are:

* The OpenShift extension is actually a wrapper extension that brings together the kubernetes and container-image-s2i extensions with sensible defaults so that it’s easier for the user to get started with Quarkus on OpenShift
* Build is done by source 2 image binary build: `./mvnw clean package -Dquarkus.container-image.build=true`. The output of the build is an ImageStream that is configured to automatically trigger a deployment
* Build and deployment are done with the command: `./mvnw clean package -Dquarkus.kubernetes.deploy=true` 
* See [openshift options](https://quarkus.io/guides/deploying-to-kubernetes#openshift)
* Add any config map, secrets, in a `openshift.yaml` under `src/main/kubernetes`. Any resource found will be added in the generated manifests. Global modifications (e.g. labels, annotations etc) will also be applied to those resources.

```properties
quarkus.openshift.expose=true
quarkus.openshift.env-vars.my-env-var.secret=my-secret
```

To change the value of a specific property in the application properties, we can use environment variables: The convention is to convert the name of the property to uppercase and replace every dot (.) with an underscore (_). So define a config map to define those environment variables in `src/main/kubernetes` folder.

For running Quarkus app on OpenShift while developing locally so change done on code, pom, properties are sent to the remote quarkus use the following settings:

* Add in application.properties:

 ```properties
 quarkus.package.type=mutable-jar
 quarkus.live-reload.password=changeit

 ```

* set the environment variable QUARKUS_LAUNCH_DEVMODE=true
* start with `./mvnw quarkus:remote-dev -Dquarkus.live-reload.url=http://my-remote-host:8080`

This is done via a HTTP based long polling transport, that will synchronize your local workspace and the remote application via HTTP calls.

## Quarkus testing

Quarkus uses junit 5, and QuarkusTest to access to CDI and other quarkus goodies. See [the test guide here](https://quarkus.io/guides/getting-started-testing). To test via HTTP, we can use rest-assured.

Things to do:

* in the test class add @QuarkusTest
* inject the bean under test
* be sure to use the good version of maven-surefire
* set the java.util.logging system property to make sure tests will use the correct log manager:

```xml
<systemPropertyVariables>
    <java.util.logging.manager>org.jboss.logmanager.LogManager</java.util.logging.manager>
</systemPropertyVariables>
```

* Tuning tests are on port 8081 (can be changed via `quarkus.http.test-port=8083`)

Integration test uses [Rest-assured](http://rest-assured.io/) with [API doc](https://github.com/rest-assured/rest-assured/wiki/Usage).

## Generate configuration for the application

`./mvnw quarkus:generate-config` 

## Configuration

`application.properties` content is injected with code like:

```java
    @ConfigProperty(name="temperature.threshold", defaultValue="2.5")
    public double temperatureThreshold;
```

!!! Attention the field needs to be public, and then in test you need to access via getter!!
DefaultValue does not work neither.

Quarkus does much of its configuration and bootstrap at build time. But some properties are defined at run time from system properties, environment variables (in uppercase, . transformed as _ : QUARKUS_DATASOURCE_PASSWORD), using `.env` file, and then `application.property` in a `$(pwd)/target/config` folder for development test.

```java
// does fail as it return -
 Assertions.assertEquals(2.0, assessor.temperatureThreshold);
// while this works!
  Assertions.assertEquals(2.0, assessor.getTemperatureThreshold());
```

The content can be combined with environment variables See [this section in quarkus guide](https://quarkus.io/guides/config#combining-property-expressions-and-environment-variables)

Also you can access the configuration programmatically. It can be handy to achieve dynamic lookup, or retrieve configured values from classes that are neither CDI beans or JAX-RS resources. See [this paragraph](https://quarkus.io/guides/config#programmatically-access-the-configuration)

```java
String databaseName = ConfigProvider.getConfig().getValue("database.name", String.class);
Optional<String> maybeDatabaseName = ConfigProvider.getConfig().getOptionalValue("database.name", String.class);
```

Quarkus supports the notion of configuration profiles. These allow you to have multiple configuration in the same file and select between them via a profile name.

See also [Using Property Expressions](https://quarkus.io/guides/config#using-property-expressions)

## Code how to

### Get access to start and stop application events

```java
import javax.enterprise.event.Observes;
...
 void onStart(@Observes StartupEvent ev){}

 void onStop(@Observes ShutdownEvent ev) { }
```

## Reactive with Mutiny

[Mutiny](https://smallrye.io/smallrye-mutiny/) is a reactive programming library to offer a more guided API than traditional reactive framework and API. It supports asynchrony, non-blocking programming and streams, events, back-pressure and data flows.

Add the resteasy mutiny package.

```xml
        <dependency>
            <groupId>io.quarkus</groupId>
            <artifactId>quarkus-resteasy-mutiny</artifactId>
        </dependency>
```

* To asynchronously handle HTTP requests, the endpoint method must return a java.util.concurrent.CompletionStage or an `io.smallrye.mutiny.Uni`  or `io.smallrye.mutiny.Multi`(requires the quarkus-resteasy-mutiny extension).

With Mutiny both Uni and Multi expose event-driven APIs: you express what you want to do upon a given event (success, failure, etc.). These APIs are divided into groups (types of operations) to make it more expressive and avoid having 100s of methods attached to a single class.

[This section of the product documentation](https://smallrye.io/smallrye-mutiny/#_uni_and_multi) goes over some examples on how to use Uni/ Multi.

Some examples:

```java
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/json/{id}")
  // producing generic json object
  public Uni<JsonObject> getJsonObject(@PathParam String id){
        JsonObject order = new JsonObject("{\"name\": \"hello you\", \"id\": \"" + id + "\"}");
        return Uni.createFrom().item( order);
    }

    \\ Producing a bean
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/order/{id}")
    public Uni<Order> getOrderById(@PathParam String id){
        Order o = new Order();
        o.deliveryLocation="Paris/France";
        o.id=id;
        return Uni.createFrom().item(o);
    }
```


## Reactive messaging

For a quick review of the reactive messaging with Quarkus tutorial is [here](https://quarkus.io/guides/kafka)

Quick summary:

* define an application scoped bean
* using @Incoming and @Outcoming annotation with channel name
* define channel properties in `application.properties`.
* Implement Deserializer using Jsonb. See [this section](https://quarkus.io/guides/kafka#serializing-via-json-b).

Nice [chear sheet](https://lordofthejars.github.io/quarkus-cheat-sheet/#_reactive_messaging) to combine Munity, reative messaging.


TBC

## Adopting Vertx

Quarkus is based on Vert.x, and almost all network-related features rely on Vert.x / Netty. Every IO interaction passes through the non-blocking and reactive Vert.x engine. The (Vert.x) HTTP server receives the request and then routes it to the application. If the request targets a JAX-RS resource, the routing layer invokes the resource method in a worker thread and writes the response when the data is available. But if the HTTP request targets a reactive (non-blocking) route, the routing layer invokes the route on the IO thread giving lots of benefits such as higher concurrency and performance.

 ![1](https://quarkus.io/guides/images/http-reactive-sequence.png)

To fully benefit from this model, the application code should be written in a non-blocking manner.

Add the extension: `./mvnw quarkus:add-extension -Dextensions="vertx"`. Get access to Vert.x via injection: 

```java
@Inject Vertx vertx;
```

When using the Mutiny API to program in reactive approach, then the Vert.x package is `io.vertx.mutiny.core.Vertx`.

## Typical problems

### Run quarkus test with external components started with docker compose

The best approach is to avoid using docker-compose for development and use TestContainer. 

### Cheat-Sheet

* [Official](https://lordofthejars.github.io/quarkus-cheat-sheet/)

### To read

* [Using Kubernetes ConfigMaps to define your Quarkus application’s properties](https://developers.redhat.com/blog/2020/01/23/using-kubernetes-configmaps-to-define-your-quarkus-applications-properties/)
* [Reactive Programming with Quarkus - postgresql reactive with mutiny](https://medium.com/@hantsy/building-reactive-apis-with-quarkus-86bb12523da1)