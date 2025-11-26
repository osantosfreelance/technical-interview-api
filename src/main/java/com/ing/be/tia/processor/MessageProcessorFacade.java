package com.ing.be.tia.processor;

import com.ing.be.tia.data.Message;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageProcessorFacade {

    private final List<MessageProcessor> processors;

    public MessageProcessorFacade(final List<MessageProcessor> processors) {
        this.processors = processors;
    }

    /**
     * Delegates the given {@link Message} to the first {@link MessageProcessor}
     * that indicates it can process the message's type.
     * <p>
     * No SWITCH-CASE or IF-ELSE is used; instead, the correct processor is
     * dynamically selected based on the {@link MessageProcessor#canProcess}
     * contract.
     *
     * @param message the message to process, must not be {@code null}
     */
    public void process(@NonNull final Message message) {
        processors.stream()
                .filter(p -> p.canProcess(message.type()))
                .findFirst()
                .ifPresent(p -> p.process(message));
    }
}
