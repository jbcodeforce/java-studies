# Quarkus

Best source of knowledge is [the guides](https://quarkus.io/guides/)

Some quick summary:

## Create a project

```shell
mvn io.quarkus:quarkus-maven-plugin:1.5.2.Final:create \
    -DprojectGroupId=ibm.gse.eda \
    -DprojectArtifactId=app-name \
    -DclassName="ibm.gse.eda.GreetingResource" \
    -Dpath="/greeting"
cd app-name
```

## Package & run

Run with automatic compilation `./mvnw compile quarkus:dev`.

Can be packaged using `./mvnw clean package` or `./mvnw clean package -Pnative`

Start and overtide properties at runtime:

`java -Dquarkus.datasource.password=youshallnotpass -jar target/myapp-runner.jar`

for a native executable: `./target/myapp-runner -Dquarkus.datasource.password=youshallnotpass`

Can also use environment variables: Environment variables names are following the [conversion rules of Eclipse MicroProfile](https://github.com/eclipse/microprofile-config/blob/master/spec/src/main/asciidoc/configsources.asciidoc#default-configsources).

### Docker build

`./mvnw clean package -Dquarkus.container-image.build=true` and push it to repository: `./mvnw clean package -Dquarkus.container-image.push=true`

To avoid downloading all the maven jars while using multistage dockerfile and to keep the current executable started with quarkus:dev running on the same network as other dependant component, use a simple docker file for development that has java and maven:

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

### Native Build

For native build see [QUARKUS - TIPS FOR WRITING NATIVE APPLICATIONS](https://quarkus.io/guides/writing-native-applications-tips)

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

For example `./mvnw quarkus:add-extension -Dextensions="container-image-docker"`

## Quarkus testing

Quarkus uses juni5, and QuarkusTest to access to CDI and other quarkus goodies. See [the test guide here](https://quarkus.io/guides/getting-started-testing). To test via HTTP, we can use rest-assured.

Things to do:

* in the test class add @QuarkusTest
* inject the bean under test
* be sure to use the good version of maven-surefire
* set the java.util.logging system property to make sure tests will use the correct logmanager: 

```xml
<systemPropertyVariables>
    <java.util.logging.manager>org.jboss.logmanager.LogManager</java.util.logging.manager>
</systemPropertyVariables>
```

* Tunning tests are on port 8081 (can be changed via `quarkus.http.test-port=8083`)
* 

## Generate configuration for the application

`./mvnw quarkus:generate-config` 

## Configuration

application.properties content is injected with code like:

```java
    @ConfigProperty(name="temperature.threshold", defaultValue="2.5")
    public double temperatureThreshold;
```

!!! Attention the field needs to be public, and then in test you need to access via getter!!
DefaultValue does not work neither.

```java
// does fail as it return -
 Assertions.assertEquals(2.0, assessor.temperatureThreshold);
// while this works!
  Assertions.assertEquals(2.0, assessor.getTemperatureThreshold());
```

The content can be combined with environment variables See [this section in quarkus guide](https://quarkus.io/guides/config#combining-property-expressions-and-environment-variables)

## Reactive messaging

The tutorial is [here](https://quarkus.io/guides/kafka)

Quick summary:

* define an application scoped bean
* using @Incoming and @Outcoming annotation with channel name
* define channel properties in application properties.
* Implement Deserializer using Jsonb. See [this section](https://quarkus.io/guides/kafka#serializing-via-json-b).

TBC

## Adopting Vertx

Quarkus is based on Vert.x, and almost all network-related features rely on Vert.x.


