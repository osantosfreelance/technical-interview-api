package com.ing.be.tia.processor;

import com.ing.be.tia.data.Message;
import com.ing.be.tia.data.MessageType;
import org.springframework.lang.NonNull;

public interface MessageProcessor {

    boolean canProcess(@NonNull final MessageType messageType);
    void process(@NonNull final Message message);
}
