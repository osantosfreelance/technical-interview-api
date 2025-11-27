package com.ing.be.tia.facade;

import com.ing.be.tia.data.Message;
import com.ing.be.tia.processor.MessageProcessor;

import java.util.List;


public class MessageProcessorFacade {
    private final List<MessageProcessor> processors;
    public MessageProcessorFacade(List<MessageProcessor> processors) {
        this.processors = processors;
    }
    public void process(Message message) {
        processors.stream()
                .filter(p -> p.canProcess(message.type()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No processor found for this type: " + message.type()))
                .process(message);
    }

}
