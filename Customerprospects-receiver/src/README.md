# Spring Cloud Azure Service Bus Integration Code Sample for message reciever

## Key concepts

This code sample demonstrates how to use Spring Integration for Azure Service Bus to recieve message from a servie bus Queue.

## Getting started

1. Update [application.yaml].

    ```yaml
    spring:
      cloud:
        azure:
          servicebus:
            connection-string: [servicebus-namespace-connection-string]
    ```

2. Update `private static final String QUEUE_NAME = "your-queue-name";` your queue name in [CustomerprospectsReceiverController.java]

3. Run the `mvn spring-boot:run` in the root of the code sample to get the app running.

4.  Verify in your app’s logs that a similar message was posted:

        2022-03-18 12:03:45.718  INFO 27628 --- [oundedElastic-7] c.a.m.s.i.ServiceBusReceiveLinkProcessor : prefetch: '1', requested: '2', linkCredits: '0', expectedTotalCredit: '1', queuedMessages:'0', creditsToAdd: '1', messageQueue.size(): '0'
        2022-03-18 12:03:45.921  INFO 27628 --- [onPool-worker-1] c.e.c.CustomerController                 : Message '{
            "firstname": "testfirstname",
            "lastname": "testfirstname"
        }' successfully checkpointed.