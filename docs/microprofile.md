
[microprofile](https://microprofile.io/) is the java framework to develop microservice architecture. MicroProfile APIs establish an optimal foundation for developing microservices-based applications

Two way to implement the communication between services:

* **Choreography** defines business workflow as a sequence of domain events and service actions. Each involved service signals a successful or faulty fulfilment of its part of the workflow via a corresponding domain event. Other services may listen to this domain event and start working on there part of the workflow after receiving it. 
* **Orchestration**  defines one orchestrating service sends commands to the different services and receives their
responses. This could be done synchronously via RESTful calls or asynchronously with the help of queues. 

## Cross cutting concerns

Cross-cutting concerns are common to all microservice and include logging, monitoring of service health and
metrics, fault tolerance, configuration, and security.

* Support distributed logging and tracing cross microservices, that can be merged and analyzed as a whole.
* Without a single point of control, each microservice needs to know if it is healthy and perform as expected. 
* microservices architecture needs to be resilient and fault tolerant by design. This means we must not only be able to detect but also to handle any issue automatically. 
* microservice accesses configurations from multiple sources in a homogeneous and transparent way
* support a mechanism to handle distributed authentication and authorization 
* offer security context propagation 
* make sure that the original service call is not forged in the authentication  process.

## Programming model

MicroProfile utilizes a very small subset of Java EE APIs to define its core programming model:

* CDI 2.0 for Dependency Injection
* JAX-RS 2.1 for REST APIs
* JSON P 1.1 for JSON Parsing
* JSON-B 1.0 for JSON Binding 

![](images/microprofile3.png)

* **Config**: externalizes configuration and obtain config via injection from config files, environment variables, system properties, or custom resource.
    ```java
    // inject property with default value
    @Inject @ConfigProperty(„MAX _ ITEM _ COUNT _ KEY“, defaultValue=”100”)
    Integer maxItemCount;
    ```
* [Fault Tolerance](https://microprofile.io/project/eclipse/microprofile-fault-tolerance) enables us to build resilient microservices by separating the execution logic from business logic. Key aspects of the Fault Tolerance API includes well known resilience patterns like TimeOut, RetryPolicy, Fallback, Bulkhead, and Circuit Breaker processing.

    ```java
    @GET
    @Timeout(500)
    @Fallback(fallbackMethod = “getBestsellersFallback”)
    public Response getPersonalRecommendations() throws InterruptedException {
        // retrieve personal recommendations by delegating the call to
        // Personal Recommendations Microservice
    }
    public Response getBestsellersFallback() {
        // retrieve bestsellers instead of personal recommendations as fallback
        // by delegating the call to Bestsellers Service
    }
    ```

* **Health Check** lets developers define and expose a domain-specific microservices health status (“UP” or “DOWN”) so unhealthy services can be restarted by the underlying environment. Health checks are used to determine
both the liveness and readiness of a service. Determining the state of a service can be composed by a set
of verification procedures. Multiple domain-specific health checks can easily be added to a microservice
by implementing the corresponding HealthCheck interface. 

    ```java
    @Readiness
    @ApplicationScoped
    public class ConnectionPoolCheck implements HealthCheck {
        @Override
        public HealthCheckResponse call() {
            if (isConnectionPoolHealthy()) {
            return HealthCheckResponse(“customer-cp”)
            .up()
            .build();
            } else {
            return HealthCheckResponse(“customer-cp”)
            .down()
            .build();
            }
        }
    ```
    @Liveness annotation is used to assess if the microservice is running

* **Metrics** delivers details about the microservices runtime behavior by providing a unified way for MicroProfile servers to export monitoring data to management agents. Metrics also provides a common Java API for exposing their telemetry data. Metrics serves to pinpoint issues, providing long-term trend data for capacity planning, and pro-active discovery of issues (e.g. disk usage growing without bounds) via a simple to use RESTful API. Metrics can also help scheduling-systems decide when to scale the application to run on more or fewer machines.

    ```java
    @POST
    @Produces(MediaType.APPLICATION _ JSON)
    @Timed(absolute = true,
        name = „microprofile.ecommerce.checkout“,
        displayName = „check-out time“,
        description = „time of check-out process in ns“,
        unit = MetricUnits.NANOSECONDS)
    public Response checkOut(...) {
        // do some check-out specific business logic
        return Response.ok()… build();
    }
    ```

* **Open API** specification provides a set of Java interfaces and programming models that allow developers to natively produce OpenAPI v3 documents. Use JAX-RS anotations with OpenAPI annotations or start from a yml file.

* **Rest Client** simplifies building REST Clients by providing a type-safe approach for invoking RESTful services over HTTP. It uses JXRS 2.1.

* **JWT Authentication** defines a format of JSON Web Token (JWT) used as the basis for interoperable authentication and authorization by providing role-based access control (RBAC) microservice endpoints using OpenID Connect. One of the main strategies to propagate the security state from clients to services, or even from services to services, involves the use of security tokens. 

* **OpenTracing** enables services to easily participate in a distributed tracing environment by defining an API. To accomplish distributed tracing, each service must be instrumented to log messages with a correlation id that may have been propagated from an upstream service.

### Sandbox projects

* The proposal for **Long Running Actions** introduces APIs for services to coordinate activities. The main thrust of the proposal introduces an API for loosely coupled services to coordinate long running activities in such a way as to guarantee a globally consistent outcome without the need to take locks on data. 
* The **Reactive Streams Operators** specification propose a set of operators for Reactive Streams. By mapping Java Streams API but for Reactive Streams, it provide a natural API to deal with stream of data, enforcing error
propagation, completion signals, and back-pressure.
* The [Reactive Messaging](https://microprofile.io/project/eclipse/microprofile-reactive-messaging) proposal explores the question „what if Java offered a new API for handling streams of messages - either point to point or from a message broker - based on the JDK 9 Flow API or alternatively on the
JDK8 compatible Reactive Streams API - that was lighter weight and easier to use than JMS/MDBs“

## Getting started

[MicroProfile Starter](https://start.microprofile.io/) helps to generate microprofile maven project with sample code. 
The folders microprofile30/service-a and service-b have the generated code. From this code the app under JEEPlay jbcodeforce.microprofile.app defines a basic template for a REST based microservice. What need to be done is:

* JAX-RS has two key concepts for creating REST APIs. The resource itself, which is modelled as a class, and a JAX-RS application, which groups all exposed resources under a common path. See the class [BasicRestApp](https://github.com/jbcodeforce/java-studies/blob/master/JEEPlay/src/main/java/jbcodeforce/microprofile/app/BasicRestApp.java). 
* The @ApplicationPath annotation has a value that indicates the path within the WAR that the JAX-RS application accepts requests from.
* Clear have one Resource class for the same resource type. The @Path annotation on the class indicates that this resource responds to specified path of the JAX-rS application. JAX-RS maps the HTTP methods on the URL to the methods on the class. [SystemPropertiesResource](https://github.com/jbcodeforce/java-studies/blob/master/JEEPlay/src/main/java/jbcodeforce/microprofile/app/SystemPropertiesResource.java)
* The JAX-RS 2.1 specification mandates JSON-Binding (JSON-B) and JAX-B.

http://localhost:9080/JEEPlay/System/properties

## Concepts

### CDI 

Use Contexts and Dependency Injection (CDI) to manage scopes and inject dependencies into microservices. 
The most fundamental services that are provided by CDI are contexts that bind the lifecycle of stateful components to well-defined contexts, and dependency injection that is the ability to inject components into an application in a typesafe way. With CDI, the container does all the daunting work of instantiating dependencies, and controlling exactly when and how these components are instantiated and destroyed.

Scopes are defined by using CDI annotations. 

When a bean needs to be persistent between all of the clients (singleton), use the `@ApplicationScoped` annotation. This annotation indicates that this particular bean is to be initialized once per application. By making it application-scoped, the container ensures that the same instance of the bean is used whenever it is injected into the application.

Add the `@RequestScoped` annotation on the class to indicate that this bean is to be initialized once for every request. Request scope is short-lived and is therefore ideal for HTTP requests.

The `@Inject` annotation indicates a dependency injection. 
