package com.ing.be.tia.service;

import com.ing.be.tia.data.Message;
import com.ing.be.tia.data.MessageType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@ExtendWith(OutputCaptureExtension.class)
class EmbeddedKafkaIntegrationTest {

    @Autowired
    private KafkaConsumer consumer;

    @Autowired
    private KafkaProducer producer;

    @Value("${spring.kafka.topic}")
    private String topic;

    @Test
    void givenEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived()
            throws Exception {
        final String data = "Sending with our own simple KafkaProducer";
        final var message = new Message(MessageType.CREATE, data);

        producer.send(topic, message);

        final var messageConsumed = consumer.getQueue().poll(10, TimeUnit.SECONDS);
        assertNotNull(messageConsumed);
        assertEquals(messageConsumed, messageConsumed);
    }

    // See TODO on KafkaConsumer.receive() method
    @Test
    void performActionsFromMultipleMessagesInAnyOrder(final CapturedOutput capturedOutput) throws InterruptedException {
        final var createMessage = new Message(MessageType.CREATE, "Trigger CREATE action");
        final var readMessage = new Message(MessageType.READ, "Trigger READ action");
        final var updateMessage = new Message(MessageType.UPDATE, "Trigger UPDATE action");
        final var deleteMessage = new Message(MessageType.DELETE, "Trigger DELETE action");

        producer.send(topic, createMessage);
        producer.send(topic, readMessage);
        producer.send(topic, updateMessage);
        producer.send(topic, deleteMessage);

        Message consumedMessage;
        do {
            consumedMessage = consumer.getQueue().poll(10, TimeUnit.SECONDS);
        } while (consumedMessage != null);

        assertTrue(capturedOutput.getOut().contains("Processing CREATE message: " + createMessage));
        assertTrue(capturedOutput.getOut().contains("Processing READ message: " + createMessage));
        assertTrue(capturedOutput.getOut().contains("Processing UPDATE message: " + createMessage));
        assertTrue(capturedOutput.getOut().contains("Processing DELETE message: " + createMessage));
    }
}