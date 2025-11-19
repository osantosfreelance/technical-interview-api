package com.ing.be.tia.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.be.tia.data.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;

@Component
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    private final ArrayBlockingQueue<Message> queue = new ArrayBlockingQueue<>(100);
    private final ObjectMapper mapper;

    // HINT: might need to inject more dependencies here
    @Autowired
    public KafkaConsumer(final ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @KafkaListener(topics = "${spring.kafka.topic}")
    public void receive(ConsumerRecord<?, String> consumerRecord) throws JsonProcessingException {
        LOGGER.info("received payload='{}'", consumerRecord);
        final var message = mapper.readValue(consumerRecord.value(), Message.class);
        queue.offer(message);

        // TODO: add message handling to trigger processors. Do not use SWITCH-CASE or IF-ELSE.
        // See EmbeddedKafkaIntegrationTest.performActionsFromMultipleMessagesInAnyOrder()
    }

    public ArrayBlockingQueue<Message> getQueue() {
        return this.queue;
    }

    public void reset() {
        queue.clear();
    }
}