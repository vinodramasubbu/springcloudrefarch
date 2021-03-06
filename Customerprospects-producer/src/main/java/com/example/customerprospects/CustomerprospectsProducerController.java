package com.example.customerprospects;

import com.azure.spring.integration.core.DefaultMessageHandler;
import com.azure.spring.integration.servicebus.queue.ServiceBusQueueOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import org.springframework.util.concurrent.ListenableFutureCallback;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerprospectsProducerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerprospectsProducerController.class);
    private static final String OUTPUT_CHANNEL = "queue.output";
    private static final String QUEUE_NAME = "customerprospects";

    @Autowired
    QueueOutboundGateway messagingGateway;

    @PostMapping("/postcustomerdata")
    //public String send(@RequestParam("message") String message) {   
    public String newCustomer(@RequestBody String message) {
        this.messagingGateway.send(message);
        return message;
    }

    @Bean
    @ServiceActivator(inputChannel = OUTPUT_CHANNEL)
    public MessageHandler queueMessageSender(ServiceBusQueueOperation queueOperation) {
        DefaultMessageHandler handler = new DefaultMessageHandler(QUEUE_NAME, queueOperation);
        handler.setSendCallback(new ListenableFutureCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                LOGGER.info("Message was sent successfully.");
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER.info("There was an error sending the message.");
            }
        });

        return handler;
    }

    /**
     * Message gateway binding with {@link MessageHandler}
     * via {@link MessageChannel} has name {@value OUTPUT_CHANNEL}
     */
    @MessagingGateway(defaultRequestChannel = OUTPUT_CHANNEL)
    public interface QueueOutboundGateway {
        void send(String text);
    }

}