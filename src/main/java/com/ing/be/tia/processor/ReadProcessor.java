package com.ing.be.tia.processor;

import com.ing.be.tia.data.Message;
import com.ing.be.tia.data.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ReadProcessor implements MessageProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadProcessor.class);

    @Override
    public boolean canProcess(@NonNull final MessageType messageType) {
        return MessageType.READ == messageType;
    }

    @Override
    public void process(@NonNull final Message message) {
        LOGGER.info("Processing READ message: {}", message);
    }
}
