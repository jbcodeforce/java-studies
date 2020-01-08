# Kafka maven archetype 

Kafka Play is a maven archetype template project with Kafka consumer and producer
deployed in Java microprofile microservice to be used for event driven microservice.


## Code structure

The code is reusing the Domain Driven Design approach layers to organize the code:

* infrastructure
* domain
* app

### Microprofile

The following features are used:

* Health
* Fault tolerance


## Build & Run

### Pre-requisite

Have a kafka cluster accessible on the cloud or on private servers.

### Build

```
mvn clean package && docker build -t ibmcase/KafkaPlay .
```

### Run

```
docker rm -f KafkaPlay || true && docker run -d -p 8080:8080 -p 4848:4848 --name KafkaPlay jbcodeforce/KafkaPlay 
```