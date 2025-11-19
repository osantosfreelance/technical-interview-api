package com.ing.be.tia.processor;

import com.ing.be.tia.data.Message;
import org.springframework.lang.NonNull;

public interface MessageProcessor {

    boolean canProcess(@NonNull final String messageType);
    void process(@NonNull final Message message);
}
