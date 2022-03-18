# Spring Cloud Azure Service Bus Integration Code Sample  for message producer

## Key concepts

This code sample demonstrates how to use Spring Integration for Azure Service Bus to post message to a servie bus Queue.

## Getting started

1. Update [application.yaml].

    ```yaml
    spring:
      cloud:
        azure:
          servicebus:
            connection-string: [servicebus-namespace-connection-string]
    ```

2. Update `private static final String QUEUE_NAME = "your-queue-name";` your queue name in [CustomerprospectsProducerController.java]

3. Run the `mvn spring-boot:run` in the root of the code sample to get the app running.

4. Send a POST request to service bus topic

        $ curl -X POST -d '{"firstname": "testfisrtname","lastname": "testlastname"}' http://localhost:8080/postcustomerdata

5.  Verify in your app’s logs that a similar message was posted:

        2022-03-18 11:50:54.735  INFO 35340 --- [nio-8080-exec-2] c.a.m.s.ServiceBusSenderAsyncClient      : Sending batch with size[1].
        2022-03-18 11:50:54.876  INFO 35340 --- [ctor-executor-2] c.e.c.CustomerController                 : Message was sent successfully.