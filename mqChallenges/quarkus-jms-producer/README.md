# Quarkus based inventory producer with REST end point project

This project uses [Quarkus](https://quarkus.io/) and JMS client with MQ configuration. It generates inventory item message to update an inventory backend. 

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell
./mvnw quarkus:dev -Ddebug=5006
```

Get a MQ broker running locally or remotely.

* To run locally use the [docker-compose]() file under this main folder: `docker-compose up &`
* To run with a MQ manager deployed on IBM Cloud, define environment variables and start the quarkus app:

```
```


## Execute the demonstration

Go to the swagger UI: [http://localhost:8080/swagger-ui/#/default](http://localhost:8080/swagger-ui/#/default)

And then in the POST operation, specify the JSON to control the demo:

```json
{
  "command": "START",
  "nbOfMessages": 2
}
```

## Anatomy

The `domain` folder defines the Inventory Message to publish and the Control DTO. The [MessageResource](https://github.com/jbcodeforce/java-studies/blob/master/mqChallenges/quarkus-jms-producer/src/main/java/ibm/gse/eda/mq/start/MessageResource.java) exposes the `/mqdemo/start` end point. The service implementing the MQ message production is in [InventoryMessageProducer](https://github.com/jbcodeforce/java-studies/blob/master/mqChallenges/quarkus-jms-producer/src/main/java/ibm/gse/eda/mq/start/infrastructure/mq/InventoryMessageProducer.java). This class is the only one interesting in this app as it illustrate injection of the mq properties, the creation of the JMSFactory at the constructor level, as this class is a singleton. A JMS context, and a producer are created at each simulation call for producing n messages.

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `jms-demo-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/jms-demo-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/jms-demo-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.