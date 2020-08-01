# Vert.x

A polyglot library to do event driven non blocking app. The main website [vertx.io/](https://vertx.io/) has a lot of examples.

## Concepts

* Vert.x is not a restrictive framework, and don't force the correct way to write an application.
* Event loop thread to process request with non blocking IO. 

## Get started

Use the [app generator](https://start.vertx.io/).

## Run with docker

Using [vert.x with docker](https://vertx.io/docs/vertx-docker/).

To run a Verticle:

```
docker run -i -t -p 8080:8080 \
    -v $PWD:/verticles vertx/vertx3-exec \
    run io.vertx.sample.RandomGeneratorVerticle \
    -cp /verticles/MY_VERTICLE.jar
```


## Vert.x body of knowledge

* [Grace Jansen's Getting started with Reactive Systems](https://developer.ibm.com/technologies/java/articles/reactive-systems-getting-started/)
* [baeldung Vertx](https://www.baeldung.com/vertx)
* [Quarkus using Eclipse Vert.x](https://quarkus.io/guides/vertx)