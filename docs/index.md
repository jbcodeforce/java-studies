# Some Java studies

## Multi-module maven

This project includes different module to test different subject related to java new language
See [this tutorial](https://www.baeldung.com/maven-multi-module) for multi modules.
 
* Define an aggregator POM
```shell
mvn archetype:generate -DgroupId=jbcodeforce -DartifactId=Java-studies
```
* Change the `<packaging>` element to `pom` 
* Define each module in separate child folders with its own pom. using
```shell
mvn archetype:generate -DgroupId=jbcodeforce  -DartifactId=AlgoPlay
```

By building the project through the aggregator POM, each project that has packaging type different than pom will result in a built archive file

By setting packaging to pom type, we're declaring that project will serve as a parent or an aggregator for other sub module project. A module element is added to the parent project

when running mvn package command in the parent project directory, Maven will build and test all three modules.

## Algo play 

The folder Algo play has its own pom, and the following problems are implemented:

* Searching in a graph using DPS and Transversal in a graph. See nice article [here](https://www.baeldung.com/java-graphs)

## 1.8 new features

### functional programming

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


## [FAQ](./FAQ.md)

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


