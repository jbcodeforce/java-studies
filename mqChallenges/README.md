# MQ challenges

A set of code samples to study MQ and get some code templates.

## The Event Booking demo

See the last documentation in [this note](https://jbcodeforce.github.io/techno/integration/mq/).

This is the implementation of [the MQ developer tutorial](https://developer.ibm.com/messaging/learn-mq/mq-tutorials/mq-dev-challenge/) and supports the implementation of the following solution:

The event booking system runs in a container and holds an event publisher, a MQ server hosting newTickets topic and purchase 
and confirmation queues. 

We need the JMS api jar and the MQ all client code. Here are the two curl to use

```sh
curl -o com.ibm.mq.allclient-9.3.0.0.jar https://repo1.maven.org/maven2/com/ibm/mq/com.ibm.mq.allclient/9.3.0.0/com.ibm.mq.allclient-9.3.0.0.jar
curl -o javax.jms-api-2.0.1.jar https://repo1.maven.org/maven2/javax/jms/javax.jms-api/2.0.1/javax.jms-api-2.0.1.jar
```

The `MQTicketService` folder has the ticket generator and mq broker configuration.

To build the image, which is an extension of the IBM MQ image, by adding new start scripts to configure MQ broker and deploy 
the "show event" publisher of newTicket events.

The Dockerfile is in `MQTicketService` folder. To build the custom image.

```
docker build -t jbcodeforce/mqticketservice .
```

And run it with the script `./runTicketContainer.sh`

The app should configure MQ and start the event publisher.

See the MQ console for the configuration: [ibmmq/console](https://localhost:9443/ibmmq/console) admin/passw0rd


## The reseller app

The Reseller application provides the prompt to ask the user how many of the available tickets they want to purchase.
The conference event booking system will process the request message and provide a response. 
The Reseller application will print the outcome to stdout.

Build with the shell `TicketSeller/build.sh`and run it. 