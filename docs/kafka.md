# Kafka based microservice

This is some development note to keep in mind for Kafka development. Most of the content is already defined in [those best practices](https://ibm-cloud-architecture.github.io/refarch-eda/kafka/readme/). Here the code in KafkaPlay is used as a template to start a project.

Code is under KafkaPlay with readme to explain how to build and run it.

## Personal notes

To access event streams on private cloud with truststore we need the jks file to be put under `src/main/liberty/config/resources/security` then set the env variable to this path: `export TRUSTSTORE_PATH="resources/security/certs.jks"`

### To debug an app on a running liberty server 

* Start liberty in debug mode: `mvn liberty:debug-server`
* In Eclipse use the Debug Configuration menu > Remote Java application > and then replace the port number 8000 to the one liberty is listening to (7777)

### Some errors

* [ERROR   ] CWWKZ0002E: An exception occurred while starting the application KafkaPlay. The exception message was: com.ibm.ws.container.service.state.StateChangeException: org.jboss.weld.exceptions.DefinitionException: WELD-000075: Normal scoped managed bean implementation class has a public field:  [EnhancedAnnotatedFieldImpl] @Inject @ConfigProperty public ibm.gse.eda.app.infrastructure.kafka.KafkaConfiguration.ordersTopicName
    * change to private variable.