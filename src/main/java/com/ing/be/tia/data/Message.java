package com.ing.be.tia.data;

import java.util.Objects;

public record Message(
        MessageType type,
        String content
) {
    @Override
    public String toString() {
        return "{ \"type\":\"" + type + "\", \"content\":\"" + content + "\" }";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Message message = (Message) object;
        return Objects.equals(content, message.content) && type == message.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, content);
    }
}