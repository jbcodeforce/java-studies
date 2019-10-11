# Some Java studies

## Multi-module maven

This project includes different modules to test different subject related to the java last language features
See [this tutorial](https://www.baeldung.com/maven-multi-module) for multi modules.
 
* Define an aggregator POM
```shell
mvn archetype:generate -DgroupId=jbcodeforce -DartifactId=Java-studies
```
* Change the `<packaging>` element to `pom` 
* Define each module in separate child folder with its own pom. using
```shell
mvn archetype:generate -DgroupId=jbcodeforce  -DartifactId=AlgoPlay
```

By building the project through the aggregator POM, each project that has packaging type different than pom will result in a built archive file

By setting packaging to pom type, we're declaring that project will serve as a parent or an aggregator for other sub module project. A module element is added to the parent project

When running `mvn package` command in the parent project directory, Maven will build and test all the dependant modules.

### Specific open liberty maven declaration

```
<parent>
	<groupId>net.wasdev.wlp.maven.parent</groupId>
	<artifactId>liberty-maven-app-parent</artifactId>
	<version>RELEASE</version>
</parent>
```

Properties:
```
<app.name>${project.artifactId}</app.name>
<testServerHttpPort>9080</testServerHttpPort>
<testServerHttpsPort>9443</testServerHttpsPort>
<warContext>${app.name}</warContext>
<package.file>${project.build.directory}/${app.name}.zip</package.file>
<packaging.type>usr</packaging.type>
```

## Algo play 

The folder Algo play has its own pom, and the following problems are implemented:

* Searching in a graph using DPS and Transversal in a graph. See nice article [here](https://www.baeldung.com/java-graphs)

## 1.8 new features

### Functional programming

A functional programming function is like a mathematical function, which produces an output that typically depends only on its arguments.
Functions exhibit referential transparency, which means you could replace a function
 call with its resulting value without changing the computation's meaning.
 With function Java becomes declarativem while with object it is imperative. 
 
They favor immutability, which means the state cannot change. Function doe not support variable assignments

### Lambdas

* single method classes that represent behavior
* can be assigned to a variable
* can be pass as argument to a method
* The type of any lambda is a functional interface: A functional interface is a special interface with one and only one abstract method.
	* It has one and only one abstract method.
	* It can be decorated with an optional @FunctionalInterface

### Enterprise Context

The context refers to the interface used to interact with your runtime environment. In java we have Servlet's ServletContext, JSF's FacesContext, Spring's ApplicationContext, Android's Context, JNDI's InitialContext,...

!!! note
		Recall that Java EE servers host several application component types that correspond to the tiers in a multitiered application. The Java EE server provides services to these components in the form of a container.

		* The web container is the interface between web components and the web server. The container manages the component’s life cycle, dispatches requests to application components, and provides interfaces to context data, such as information about the current request. 
		*  EJB container is the interface between enterprise beans, which provide the business logic in a Java EE application.
		* The application client container runs on the client machine and is the gateway between the client application and the Java EE server components.

javax.enterprise.context is a set of annotation APIs to define component scope. See [ApplicationScoped](https://javaee.github.io/javaee-spec/javadocs/javax/enterprise/context/ApplicationScoped.html).


## Jakarta EE 8

Jakarta EE comprises many technologies such as:

* Java Server Pages or JSP,
* Java Server Faces or JSF,
* Serverlets,
* JSTL,
* JDBC,
* Struts,
* Java Persistence API or JPA
* Hiberante ORM

### Servlet 4.0

The typical life cycle:

* First the HTTP requests coming to the server are delegated to the servlet container.
* The servlet container loads the servlet and call `init()`, before invoking the `service()` method.
* Then the servlet container handles multiple requests by spawning multiple threads, each thread executing the service() method of a single instance of the servlet.
* The servlet is terminated by calling the destroy() method.
* Finally, servlet is garbage collected by the garbage collector of the JVM

Use the Open Liberty guide from [here](https://openliberty.io/guides/maven-intro.html) to create a web app. Below is a quick summary of what was done for JEEPlay project.

* Define a server.xml file in src/main/liberty/config using the `servlet-4.0` feature. Clone [this open liberty guide](https://github.com/openliberty/guide-maven-intro.git) as source.
* Add open liberty maven app parent artifact
* Add war as project packaging
* Add the properties for the app server used in server.xml and integration tests (See pom.xml in JEEPlay folder)
* Add dependencies on servlet 4.01 api and http client. The dependency is set to provided, which means that the API is in the server runtime and doesn’t need to be packaged by the application
* Add the Liberty Maven plugin, which gives details of the name, version, and file type of Open Liberty runtime package which it will download from the public Maven repositor. The `<bootstrapProperties/>` section provides the set of variables that the `server.xml` references. The `<stripVersion/>` field removes the version number from the name of the application.
* Maven is configured to run the integration test using the maven-failsafe-plugin. The `<systemPropertyVariables/>` section defines some variables that the test class uses. The test code needs to know where to find the application that it is testing. While the port number and context root information can be hardcoded in the test class, it is better to specify it in a single place like the Maven pom.xml file because this information is also used by other files in the project.
* mvn install and then start liberty: 
	```
	mvn liberty:start-server
	```

See above steps materialized in code in JEEPlay/... [HelloServlet.java](https://github.com/jbcodeforce/java-studies/tree/master/JEEPlay/src/main/java/jbcodeforce/servlet/HelloServlet.java) and [ServletEndPointIT.java](https://github.com/jbcodeforce/java-studies/tree/master/JEEPlay/src/main/test/jbcodeforce/it/ServletEndPointIT.java).

The `javax.servlet.annotation.WebServlet` helps to replace the web.xml file and servlet section to define name, urlPattern, load on startup...
* For integration tests or connecting to remote end point, use the Apache [commons client.](https://hc.apache.org/httpcomponents-client-4.5.x/tutorial/html/index.html).

See also [this tutorial](https://www.tutorialspoint.com/servlets/index.htm) on servlet.

## Reactor

Reactor is an implementation of the Reactive Programming paradigm, which can be summed up as: it is an asynchronous programming concerned with data streams and the propagation of change.
It provides few additional aspects:

* Composability and readability
* Data as a flow manipulated with a rich vocabulary of operators
* Nothing happens until you subscribe
* Backpressure or the ability for the consumer to signal the producer that the rate of emission is too high
* High level but high value abstraction that is concurrency-agnostic

See the simplest examples of Flux code in src/test/main/jbcodeforce/react/ReactorSimpleTest.java. Then tests with the StepVerifier. StepVerifier wraps source of stream and express expectations about the next signals to occur. src/test/main/jbcodeforce/react/ReactorStepVerifier.java.


See [Project Reactor](http://projectreactor.io/docs/core/release/reference/#intro-reactive)


## MQ

This is the implementation of [the MQ developer tutorial](https://developer.ibm.com/messaging/learn-mq/mq-tutorials/mq-dev-challenge/) and supports the Reseller class outlined in the red rectangle below:
![](https://developer.ibm.com/messaging/wp-content/uploads/sites/18/2018/08/LearnMQbadgeDiag1.png)

Code to subscribe to topic, and put, get from Queue. It includes a Docker image with MQ embbedded [more explanation here.](mqChallenge/README.md)


