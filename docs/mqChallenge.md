# Publisher / Subscribe on MQ

This is the implementation of [the MQ developer tutorial](https://developer.ibm.com/messaging/learn-mq/mq-tutorials/mq-dev-challenge/) and supports the implementation of the following solution:
![](https://developer.ibm.com/messaging/wp-content/uploads/sites/18/2018/08/LearnMQbadgeDiag1.png)

The conference event system and reseller applications are loosely coupled. Asynchronous messaging allows us to integrate these components and build in a buffer, or shock absorber. Should either component lose connectivity, fail or experience fluctuations in throughput, the messaging layer will deal with any instability.

## The Event Booking

The event booking system runs in a container and holds an event publisher, MQ server hosting newTickets topic and purchase and confirmation queues. The code is in `EventBookingServer` folder.

To build the image
```
docker build -t jbcodeforce/mqbooking .
```

And run it with the script `runBookingContainer.sh`

## The reseller app

The Reseller application provides the prompt to ask the user how many of the available tickets they want to purchase.
The conference event booking system will process the request message and provide a response. 
The Reseller application will print the outcome to stdout.

Build with the shell `TicketSeller/build.sh`and run it. 